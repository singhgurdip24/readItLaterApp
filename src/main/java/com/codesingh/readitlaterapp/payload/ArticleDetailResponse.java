package com.codesingh.readitlaterapp.payload;

import java.time.Instant;

public class ArticleDetailResponse extends ArticleResponse {

  private Boolean articleRead;

  private Boolean favourite;

  private Instant savedAt;

  private Instant lastModifiedAt;

  public Instant getSavedAt() {
    return savedAt;
  }

  public void setSavedAt(Instant savedAt) {
    this.savedAt = savedAt;
  }

  public Instant getLastModifiedAt() {
    return lastModifiedAt;
  }

  public void setLastModifiedAt(Instant lastModifiedAt) {
    this.lastModifiedAt = lastModifiedAt;
  }

  public Boolean getArticleRead() {
    return articleRead;
  }

  public void setArticleRead(Boolean articleRead) {
    this.articleRead = articleRead;
  }

  public Boolean getFavourite() {
    return favourite;
  }

  public void setFavourite(Boolean favourite) {
    this.favourite = favourite;
  }
}
