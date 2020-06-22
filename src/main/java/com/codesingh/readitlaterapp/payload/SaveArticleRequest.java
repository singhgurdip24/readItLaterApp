package com.codesingh.readitlaterapp.payload;

import com.codesingh.readitlaterapp.model.audit.DateAudit;

import javax.validation.constraints.NotBlank;

public class SaveArticleRequest extends DateAudit {

  @NotBlank
  private String articleUrl;

  public String getArticleUrl() {
    return articleUrl;
  }

  public void setArticleUrl(String articleUrl) {
    this.articleUrl = articleUrl;
  }
}
