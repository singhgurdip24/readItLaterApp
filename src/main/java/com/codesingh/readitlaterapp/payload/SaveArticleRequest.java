package com.codesingh.readitlaterapp.payload;

import com.codesingh.readitlaterapp.model.audit.DateAudit;

import javax.validation.constraints.NotBlank;

public class SaveArticleRequest extends DateAudit {

  @NotBlank
  private String articleUrl;

  private String description;

  private String type;

  private String author;

  private Boolean markAsRead;

  private Boolean markAsFavourite;

  public Boolean getMarkAsRead() {
    return markAsRead;
  }

  public void setMarkAsRead(Boolean markAsRead) {
    this.markAsRead = markAsRead;
  }

  public Boolean getMarkAsFavourite() {
    return markAsFavourite;
  }

  public void setMarkAsFavourite(Boolean markAsFavourite) {
    this.markAsFavourite = markAsFavourite;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
