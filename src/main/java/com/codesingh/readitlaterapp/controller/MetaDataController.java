package com.codesingh.readitlaterapp.controller;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meta")
public class MetaDataController {

  @GetMapping("/url")
  public String getMetaData() throws IOException {
    Document doc = Jsoup.connect("https://medium.com/better-marketing/59-conversation-starters-to-get-the-most-out-of-networking-events-f7aca2795c83").get();
    Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
    for (Element image : images) {
      System.out.println("src : " + image.attr("src"));
      System.out.println("height : " + image.attr("height"));
      System.out.println("width : " + image.attr("width"));
      System.out.println("alt : " + image.attr("alt"));
    }
    Elements links = doc.select("a[href]");
    for (int i=5; i< 15;i++){
      System.out.println(links.get(i).toString());
    }
//    for (Element link : links) {
//      System.out.println(link.toString());
//    }
    return "got metadata";
  }
}
