package com.codesingh.readitlaterapp.controller;

import com.codesingh.readitlaterapp.payload.ArticleResponse;
import com.codesingh.readitlaterapp.payload.PagedResponse;
import com.codesingh.readitlaterapp.service.ArticleService;
import com.codesingh.readitlaterapp.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

@RestController
public class UserController {

  @Autowired
  ArticleService articleService;

//  @GetMapping("/articles")
//  public PagedResponse<ArticleResponse> getAllArticles(
//    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
//  ){
//    return articleService.getAllArticles(page,size);
//  }

  @GetMapping("/articles")
  public ArticleResponse getAllArticles(
    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
  ){
    return articleService.getAllArticles(page,size);
  }
}
