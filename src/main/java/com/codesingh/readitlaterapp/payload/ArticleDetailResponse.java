package com.codesingh.readitlaterapp.payload;

import java.time.Instant;

public class ArticleDetailResponse {

  public Long id;

  public String articleUrl;

  public String description;

  public String title;

  public String author;

  public String imageUrl;

  public String type;

  public Instant savedAt;

  public Instant updatedAt;

  public Boolean articleRead;

  public Boolean isFavourite;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getArticleUrl() {
    return articleUrl;
  }

  public void setArticleUrl(String articleUrl) {
    this.articleUrl = articleUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Instant getSavedAt() {
    return savedAt;
  }

  public void setSavedAt(Instant savedAt) {
    this.savedAt = savedAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Boolean getArticleRead() {
    return articleRead;
  }

  public void setArticleRead(Boolean articleRead) {
    this.articleRead = articleRead;
  }

  public Boolean getFavourite() {
    return isFavourite;
  }

  public void setFavourite(Boolean favourite) {
    isFavourite = favourite;
  }
}
