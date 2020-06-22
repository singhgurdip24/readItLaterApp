package com.codesingh.readitlaterapp.model;

import com.codesingh.readitlaterapp.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "articles", uniqueConstraints = {
  @UniqueConstraint(columnNames = {
    "url"
  })
})
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  public String url;

  public String description;

  public String author;

  public String imageUrl;

  public String title;

  @OneToMany(mappedBy = "article")
  Set<UserArticleMap> userArticleMapSet;

  public Article() {
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

  public Set<UserArticleMap> getUserArticleMapSet() {
    return userArticleMapSet;
  }

  public void setUserArticleMapSet(Set<UserArticleMap> userArticleMapSet) {
    this.userArticleMapSet = userArticleMapSet;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
