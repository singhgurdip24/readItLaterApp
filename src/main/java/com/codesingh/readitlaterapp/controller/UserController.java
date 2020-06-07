package com.codesingh.readitlaterapp.controller;

import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.payload.*;
import com.codesingh.readitlaterapp.security.CurrentUser;
import com.codesingh.readitlaterapp.security.UserPrincipal;
import com.codesingh.readitlaterapp.service.ArticleService;
import com.codesingh.readitlaterapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/")
public class UserController {

  @Autowired
  ArticleService articleService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/articles")
  public PagedResponse<ArticleResponse> getAllArticles(
    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
  ){
    return articleService.getAllArticles(page,size);
  }

  @GetMapping("/users/{username}/articles")
  public PagedResponse<ArticleDetailResponse> getAllUserArticles(
    @PathVariable(value = "username") String username,
    @CurrentUser UserPrincipal currentUser,
    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
  ){
    return articleService.getAllUserArticles(username,currentUser,page,size);
  }

  @PostMapping("/articles")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> postUserArticle(
    @Valid @RequestBody SaveArticleRequest saveArticleRequest,
    @CurrentUser UserPrincipal currentUser
  ) {
    logger.info("here");
    Article article = articleService.saveArticleByUser(saveArticleRequest, currentUser);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest().path("articles/{articleId}")
      .buildAndExpand(article.getId()).toUri();

    return ResponseEntity.created(location)
      .body(new ApiResponse(true, "Article Saved Successfully"));
  }

  @DeleteMapping(value = "/users/{username}/articles/{id}")
  public ResponseEntity<Long> deleteArticle(
    @PathVariable(value = "id") Long id,
    @PathVariable(value = "username") String username
  ) {

    Boolean isRemoved = articleService.deleteArticleMap(id,username);

    if (!isRemoved) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(id, HttpStatus.OK);
  }

}
