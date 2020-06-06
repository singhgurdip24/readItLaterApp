package com.codesingh.readitlaterapp.util;

import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.model.UserArticleMap;
import com.codesingh.readitlaterapp.payload.ArticleDetailResponse;
import com.codesingh.readitlaterapp.payload.ArticleResponse;

public class ModelMapper {

  public static ArticleResponse mapArticleToArticleResponse(Article article){

    ArticleResponse articleResponse = new ArticleResponse();
    articleResponse.setId(article.getId());
    articleResponse.setUrl(article.getUrl());
    articleResponse.setDescription(article.getDescription());
    articleResponse.setAuthor(article.getAuthor());
    articleResponse.setType(article.getType());
    articleResponse.setCreatedAt(article.getCreatedAt());

    return articleResponse;
  }

  public static ArticleDetailResponse mapArticleMapToArticleDetailResponse(UserArticleMap userArticleMap){

    ArticleDetailResponse articleDetailResponse = new ArticleDetailResponse();
    articleDetailResponse.setId(userArticleMap.getMap_id());
    articleDetailResponse.setUrl(userArticleMap.getArticle().getUrl());
    articleDetailResponse.setDescription(userArticleMap.getArticle().getDescription());
    articleDetailResponse.setAuthor(userArticleMap.getArticle().getAuthor());
    articleDetailResponse.setType(userArticleMap.getArticle().getType());
    articleDetailResponse.setCreatedAt(userArticleMap.getArticle().getCreatedAt());
    articleDetailResponse.setArticleRead(userArticleMap.getRead());
    articleDetailResponse.setArticleRead(userArticleMap.getFavourite());
    articleDetailResponse.setSavedAt(userArticleMap.getCreatedDate());
    articleDetailResponse.setLastModifiedAt(userArticleMap.getLastModifiedDate());

    return articleDetailResponse;
  }
}
