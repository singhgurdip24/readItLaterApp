package com.codesingh.readitlaterapp.payload;

public class ArticleMetaResponse extends ArticleDetailResponse{

  private String metaAuthor;

  private String metaDescription;

  private String image;

  private String title;


  public String getMetaDescription() {
    return metaDescription;
  }

  public void setMetaDescription(String metaDescription) {
    this.metaDescription = metaDescription;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMetaAuthor() {
    return metaAuthor;
  }

  public void setMetaAuthor(String metaAuthor) {
    this.metaAuthor = metaAuthor;
  }
}
