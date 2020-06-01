package com.codesingh.readitlaterapp.repository;

import com.codesingh.readitlaterapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
  Optional<Article> findById(Long id);

  List<Article> findByAuthor(String author);
}
