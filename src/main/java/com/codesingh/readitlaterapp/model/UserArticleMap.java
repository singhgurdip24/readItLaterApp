package com.codesingh.readitlaterapp.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_article_map")
public class UserArticleMap {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JoinColumn(name = "map_id")
  private Long mapId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @CreatedDate
  @Column(name = "created_date")
  private Instant createdDate;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Instant lastModifiedDate;

  private Boolean articleRead;

  private Boolean favourite;

  public UserArticleMap() {
  }

  public UserArticleMap(Long mapId, User user, Article article, Instant createdDate, Instant lastModifiedDate, Boolean articleRead, Boolean favourite) {
    this.mapId = mapId;
    this.user = user;
    this.article = article;
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
    this.articleRead = articleRead;
    this.favourite = favourite;
  }

  public Long getMap_id() {
    return mapId;
  }

  public void setMap_id(Long map_id) {
    this.mapId = map_id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Instant getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Instant lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Boolean getRead() {
    return articleRead;
  }

  public void setRead(Boolean articleRead) {
    this.articleRead = articleRead;
  }

  public Boolean getFavourite() {
    return favourite;
  }

  public void setFavourite(Boolean favourite) {
    this.favourite = favourite;
  }
}
