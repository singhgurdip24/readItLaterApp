package com.codesingh.readitlaterapp.repository;

import com.codesingh.readitlaterapp.model.UserArticleMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserArticleMapRepository extends JpaRepository<UserArticleMap,Long> {
  Optional<UserArticleMap> findById(Long id);

  Page<UserArticleMap> findByUserId(Long id, Pageable pageable);

  Optional<UserArticleMap> findByArticleId(Long id);

  Optional<UserArticleMap> findByArticleIdAndUserId(Long articleId, Long userId);

}
