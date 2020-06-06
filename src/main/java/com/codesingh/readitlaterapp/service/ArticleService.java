package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.exception.BadRequestException;
import com.codesingh.readitlaterapp.exception.ResourceNotFoundException;
import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.model.User;
import com.codesingh.readitlaterapp.model.UserArticleMap;
import com.codesingh.readitlaterapp.payload.ArticleDetailResponse;
import com.codesingh.readitlaterapp.payload.ArticleResponse;
import com.codesingh.readitlaterapp.payload.PagedResponse;
import com.codesingh.readitlaterapp.repository.ArticleRepository;
import com.codesingh.readitlaterapp.repository.UserArticleMapRepository;
import com.codesingh.readitlaterapp.repository.UserRepository;
import com.codesingh.readitlaterapp.security.UserPrincipal;
import com.codesingh.readitlaterapp.util.AppConstants;
import com.codesingh.readitlaterapp.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private UserArticleMapRepository userArticleMapRepository;

  @Autowired
  private UserRepository userRepository;

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

  public PagedResponse<ArticleDetailResponse> getAllUserArticles(String username, UserPrincipal currentUser, int page, int size) {
    validatePageNumberAndSize(page, size);

    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    Pageable pageable = PageRequest.of(page,size,Sort.by("mapId"));
    Page<UserArticleMap> articleMaps =  userArticleMapRepository.findByUserId(user.getId(),pageable);

    if(articleMaps.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), articleMaps.getNumber(),
        articleMaps.getSize(), articleMaps.getTotalElements(), articleMaps.getTotalPages(), articleMaps.isLast());
    }

    List<ArticleDetailResponse> articleResponses = articleMaps.map(
      ModelMapper::mapArticleMapToArticleDetailResponse).getContent();

    return new PagedResponse<>(articleResponses, articleMaps.getNumber(),
      articleMaps.getSize(),articleMaps.getNumberOfElements(),articleMaps.getTotalPages(),articleMaps.isLast());

  }
}
