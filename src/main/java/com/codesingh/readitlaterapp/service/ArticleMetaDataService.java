package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.payload.ArticleDetailResponse;
import com.codesingh.readitlaterapp.payload.ArticleMetaResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArticleMetaDataService {

  public ArticleMetaDataService() {
  }

  /*
  public ArticleMetaResponse getArticleMetaData(String articleUrl) throws IOException {
    Document doc = Jsoup.connect(articleUrl).get();

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

    ArticleMetaResponse articleMetaResponse = new ArticleMetaResponse();
    articleMetaResponse.setAuthor(author);
    articleMetaResponse.setDescription(description);
    articleMetaResponse.setImage(image);
    articleMetaResponse.setTitle(title);

    return articleMetaResponse;
  }

   */

  public ArticleMetaResponse getArticleMetaData(ArticleDetailResponse articleDetailResponse) throws IOException {
    Document doc = Jsoup.connect(articleDetailResponse.getUrl()).get();

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

    ArticleMetaResponse articleMetaResponse = new ArticleMetaResponse();
    articleMetaResponse.setMetaAuthor(author);
    articleMetaResponse.setMetaDescription(description);
    articleMetaResponse.setImage(image);
    articleMetaResponse.setTitle(title);

    articleMetaResponse.setUrl(articleDetailResponse.getUrl());
    articleMetaResponse.setId(articleDetailResponse.getId());
    articleMetaResponse.setCreatedAt(articleDetailResponse.getCreatedAt());
    articleMetaResponse.setType(articleDetailResponse.getType());
    articleMetaResponse.setArticleRead(articleDetailResponse.getArticleRead());
    articleMetaResponse.setFavourite(articleDetailResponse.getFavourite());
    articleMetaResponse.setLastModifiedAt(articleDetailResponse.getLastModifiedAt());
    articleMetaResponse.setSavedAt(articleDetailResponse.getSavedAt());

    return articleMetaResponse;
  }
}
