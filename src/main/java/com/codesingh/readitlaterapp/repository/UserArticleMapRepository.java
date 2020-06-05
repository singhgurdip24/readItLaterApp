package com.codesingh.readitlaterapp.repository;

import com.codesingh.readitlaterapp.model.UserArticleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserArticleMapRepository extends JpaRepository<UserArticleMap,Long> {
  Optional<UserArticleMap> findById(Long id);

  List<UserArticleMap> findByUserId(Long id);

}
