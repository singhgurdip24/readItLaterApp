package com.codesingh.readitlaterapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

public class ArticleResponse {

  private Long id;

  private String url;

  private String description;

  private Instant createdAt;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String author;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String type;

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
