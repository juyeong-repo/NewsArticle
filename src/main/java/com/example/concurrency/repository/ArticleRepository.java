package com.example.concurrency.repository;

import com.example.concurrency.domain.Article;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Article s where s.articleId = :articleId")
    Article findByIdWithOptimisticLock(Long articleId);
}



