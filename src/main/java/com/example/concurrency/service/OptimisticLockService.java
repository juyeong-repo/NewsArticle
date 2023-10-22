package com.example.concurrency.service;

import com.example.concurrency.domain.Article;
import com.example.concurrency.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ArticleRepository articleRepository;


    public OptimisticLockService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void updateArticle(Long articleId, String title, String content){
        Article article = articleRepository.findByIdWithOptimisticLock(articleId);
        article.updateArticle(1L,"낙관적 잠금", "테스트");

        articleRepository.saveAndFlush(article);
    }
}
