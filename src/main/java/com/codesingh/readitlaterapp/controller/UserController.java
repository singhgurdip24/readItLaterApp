package com.codesingh.readitlaterapp.controller;

import com.codesingh.readitlaterapp.payload.ArticleDetailResponse;
import com.codesingh.readitlaterapp.payload.ArticleResponse;
import com.codesingh.readitlaterapp.payload.PagedResponse;
import com.codesingh.readitlaterapp.security.CurrentUser;
import com.codesingh.readitlaterapp.security.UserPrincipal;
import com.codesingh.readitlaterapp.service.ArticleService;
import com.codesingh.readitlaterapp.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class UserController {

  @Autowired
  ArticleService articleService;

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

//  @GetMapping("/users/{username}/articles")
//  public Integer getAllUserArticles(
//    @PathVariable(value = "username") String username,
//    @CurrentUser UserPrincipal currentUser,
//    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
//  ){
//    return articleService.getAllUserArticles(username,currentUser,page,size);
//  }

}
