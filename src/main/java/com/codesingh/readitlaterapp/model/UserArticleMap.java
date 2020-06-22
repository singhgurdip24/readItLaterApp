package com.codesingh.readitlaterapp.model;

import com.codesingh.readitlaterapp.model.audit.DateAudit;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_article_map")
public class UserArticleMap extends DateAudit {

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

  @Column(name = "deleted_at")
  private Instant deletedAt;

  private Boolean articleRead;

  private Boolean favourite;

  private Boolean isDeleted;

  public UserArticleMap() {
  }

  public Long getMapId() {
    return mapId;
  }

  public void setMapId(Long mapId) {
    this.mapId = mapId;
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

  public Instant getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Instant deletedAt) {
    this.deletedAt = deletedAt;
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

  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }
}
