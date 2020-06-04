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
public class Article extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  public String url;

  public String description;

  public String author;

  public String type;

  @OneToMany(mappedBy = "article")
  Set<UserArticleMap> userArticleMapSet;

  public Article() {
  }

  public Article(String url, String description, String author, String type) {
    this.url = url;
    this.description = description;
    this.author = author;
    this.type = type;
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
