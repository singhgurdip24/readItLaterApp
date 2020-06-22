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
    articleResponse.setImageUrl(article.getImageUrl());
    articleResponse.setTitle(article.getTitle());

    return articleResponse;
  }

  public static ArticleDetailResponse mapArticleMapToArticleDetailResponse(UserArticleMap userArticleMap){

    ArticleDetailResponse articleDetailResponse = new ArticleDetailResponse();
    articleDetailResponse.setId(userArticleMap.getMapId());
    articleDetailResponse.setArticleUrl(userArticleMap.getArticle().getUrl());
    articleDetailResponse.setDescription(userArticleMap.getArticle().getDescription());
    articleDetailResponse.setAuthor(userArticleMap.getArticle().getAuthor());
    articleDetailResponse.setSavedAt(userArticleMap.getCreatedAt());
    articleDetailResponse.setArticleRead(userArticleMap.getArticleRead());
    articleDetailResponse.setFavourite(userArticleMap.getFavourite());
    articleDetailResponse.setUpdatedAt(userArticleMap.getUpdatedAt());
    articleDetailResponse.setImageUrl(userArticleMap.getArticle().getImageUrl());
    articleDetailResponse.setTitle(userArticleMap.getArticle().getTitle());

    return articleDetailResponse;
  }


}
