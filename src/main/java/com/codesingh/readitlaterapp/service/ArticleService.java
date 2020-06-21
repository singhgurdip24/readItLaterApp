package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.controller.UserController;
import com.codesingh.readitlaterapp.exception.BadRequestException;
import com.codesingh.readitlaterapp.exception.ResourceNotFoundException;
import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.model.User;
import com.codesingh.readitlaterapp.model.UserArticleMap;
import com.codesingh.readitlaterapp.payload.*;
import com.codesingh.readitlaterapp.repository.ArticleRepository;
import com.codesingh.readitlaterapp.repository.UserArticleMapRepository;
import com.codesingh.readitlaterapp.repository.UserRepository;
import com.codesingh.readitlaterapp.security.UserPrincipal;
import com.codesingh.readitlaterapp.util.AppConstants;
import com.codesingh.readitlaterapp.util.ModelMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private UserArticleMapRepository userArticleMapRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ArticleMetaDataService articleMetaDataService;

  public PagedResponse<ArticleResponse> getAllArticles(int page, int size){

    validatePageNumberAndSize(page, size);

    Pageable pageable = PageRequest.of(page,size,Sort.by("id"));
    Page<Article> articles = articleRepository.findAll(pageable);

    if(articles.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), articles.getNumber(),
        articles.getSize(), articles.getTotalElements(), articles.getTotalPages(), articles.isLast());
    }

    List<ArticleResponse> articleResponses = articles.map(
      article -> {return ModelMapper.mapArticleToArticleResponse(article);}
    ).getContent();

    return new PagedResponse<>(articleResponses, articles.getNumber(),
      articles.getSize(),articles.getNumberOfElements(),articles.getTotalPages(),articles.isLast());
  }

  private void validatePageNumberAndSize(int page, int size) {
    if(page < 0) {
      throw new BadRequestException("Page number cannot be less than zero.");
    }

    if(size > AppConstants.MAX_PAGE_SIZE) {
      throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
    }
  }

  public PagedResponse<ArticleMetaResponse> getAllUserArticles(String username, UserPrincipal currentUser, int page, int size) {

    List<ArticleDetailResponse> articleResponsesFilter = new ArrayList<>();

    validatePageNumberAndSize(page, size);

    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    Pageable pageable = PageRequest.of(page,size,Sort.by("mapId"));
    Page<UserArticleMap> articleMaps =  userArticleMapRepository.findByUserId(user.getId(),pageable);

    if(articleMaps.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), articleMaps.getNumber(),
        articleMaps.getSize(), articleMaps.getTotalElements(), articleMaps.getTotalPages(), articleMaps.isLast());
    }

    List<ArticleDetailResponse> articleResponses = articleMaps
      .map(ModelMapper::mapArticleMapToArticleDetailResponse)
      .getContent();

    for (ArticleDetailResponse articleResponse: articleResponses) {
      if (articleResponse.getDeletedAt() == null) {
        articleResponsesFilter.add(articleResponse);
      }
    }

    List<ArticleMetaResponse> articleMetaResponses = articleResponses.stream().map(
      articleResponse -> {
        try {
          return articleMetaDataService.getArticleMetaData(articleResponse);
        } catch (IOException e) {
          e.printStackTrace();
          return new ArticleMetaResponse();
        }
      }
    ).collect(Collectors.toList());

    return new PagedResponse<>(articleMetaResponses, articleMaps.getNumber(),
      articleMaps.getSize(),articleMaps.getNumberOfElements(),articleMaps.getTotalPages(),articleMaps.isLast());

  }

  public Article saveArticleByUser(SaveArticleRequest saveArticleRequest, UserPrincipal currentUser) throws IOException {
    Article article = new Article();
    article.setUrl(saveArticleRequest.getArticleUrl());
    article.setAuthor(saveArticleRequest.getAuthor());
    article.setDescription(saveArticleRequest.getDescription());
    article.setType(saveArticleRequest.getType());
    Instant now = Instant.now();
    article.setCreatedAt(now);
    article.setUpdatedAt(now);

    Document doc = Jsoup.connect(saveArticleRequest.getArticleUrl()).get();

    Elements metaTags = doc.getElementsByTag("meta");

    String title = "" ;
    String image = "";
    String description = "";
    String author = "";

    for (Element metaTag : metaTags) {
      if(metaTag.attr("property").equals("og:image")){
        image = metaTag.attr("content").toString();
      }
      if(metaTag.attr("property").equals("og:description")){
        description = metaTag.attr("content").toString();
      }
      if(metaTag.attr("name").equals("author")){
        author = metaTag.attr("content").toString();
      }
      if(metaTag.attr("property").equals("og:title")){
        title = metaTag.attr("content").toString();
      }
    }

    System.out.println("image" + image);
    System.out.println("description" + description);
    System.out.println("author" + author);
    System.out.println("title" + title);

    articleRepository.save(article);

    if(currentUser != null) {
      User user = userRepository.findByUsername(currentUser.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));

      UserArticleMap userArticleMap = new UserArticleMap();
      userArticleMap.setArticle(article);
      userArticleMap.setCreatedDate(now);
      userArticleMap.setRead(saveArticleRequest.getMarkAsRead());
      userArticleMap.setFavourite(saveArticleRequest.getMarkAsFavourite());
      userArticleMap.setUser(user);

      userArticleMapRepository.save(userArticleMap);
    }
    return article;
  }

  public Boolean deleteArticleMap(Long id, String username) {

    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    Optional<UserArticleMap> userArticleMap = userArticleMapRepository.findByArticleIdAndUserId(id,user.getId());

    if(!userArticleMap.isPresent()){
      return Boolean.FALSE;
    }

    UserArticleMap map = userArticleMap.get();
    map.setDeletedAt(Instant.now());
    userArticleMapRepository.save(map);
    return Boolean.TRUE;
  }

  public ArticleDetailResponse getUserArticleDetail(Long articleId, String username) {

    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    UserArticleMap userArticleMap = userArticleMapRepository.findByArticleIdAndUserId(articleId,user.getId()).orElseThrow(
      () -> new ResourceNotFoundException("Article", "Not Found" , "")
    );

    return ModelMapper.mapArticleMapToArticleDetailResponse(userArticleMap);
  }
}
