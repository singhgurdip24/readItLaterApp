package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.payload.SaveArticleRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class ArticleMetaDataService {

  public ArticleMetaDataService() {
  }

  public HashMap<String,String> getArticleMetaData(SaveArticleRequest saveArticleRequest) throws IOException {

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

    HashMap<String, String> metaMap = new HashMap<>();
    metaMap.put("image",image);
    metaMap.put("description", description);
    metaMap.put("title", title);
    metaMap.put("author",author);

    return metaMap;
  }
}
