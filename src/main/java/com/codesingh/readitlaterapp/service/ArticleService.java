package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.exception.BadRequestException;
import com.codesingh.readitlaterapp.model.Article;
import com.codesingh.readitlaterapp.payload.ArticleResponse;
import com.codesingh.readitlaterapp.payload.PagedResponse;
import com.codesingh.readitlaterapp.repository.ArticleRepository;
import com.codesingh.readitlaterapp.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collections;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public PagedResponse<ArticleResponse> getAllArticles(int page, int size){
    validatePageNumberAndSize(page, size);

    Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC);
    Page<Article> articles = articleRepository.findAll(pageable);

    if(articles.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), articles.getNumber(),
        articles.getSize(), articles.getTotalElements(), articles.getTotalPages(), articles.isLast());
    }

    
  }

  private void validatePageNumberAndSize(int page, int size) {
    if(page < 0) {
      throw new BadRequestException("Page number cannot be less than zero.");
    }

    if(size > AppConstants.MAX_PAGE_SIZE) {
      throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
    }
  }
}