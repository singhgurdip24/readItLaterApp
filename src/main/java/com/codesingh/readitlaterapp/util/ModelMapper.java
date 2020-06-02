package com.codesingh.readitlaterapp.util;

import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.payload.ArticleResponse;

public class ModelMapper {

  public static ArticleResponse mapArticleToArticleResponse(Article article){

    ArticleResponse articleResponse = new ArticleResponse();
    articleResponse.setId(article.getId());
    articleResponse.setUrl(article.getUrl());
    articleResponse.setDescription(article.getDescription());
    articleResponse.setAuthor(article.getAuthor());
    articleResponse.setType(article.getType());

    return articleResponse;
  }
}
