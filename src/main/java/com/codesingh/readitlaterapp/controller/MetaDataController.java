package com.codesingh.readitlaterapp.controller;

import java.io.IOException;

import com.codesingh.readitlaterapp.payload.ArticleMetaResponse;
import com.codesingh.readitlaterapp.util.AppConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meta")
public class MetaDataController {

  @GetMapping("/url")
  public ArticleMetaResponse getMetaData(
    @RequestParam(value = "articleUrl") String url
  ) throws IOException {
    Document doc = Jsoup.connect(url).get();

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
    articleMetaResponse.setId(Long.parseLong("1"));
    articleMetaResponse.setUrl(url);

    return articleMetaResponse;
  }
}
