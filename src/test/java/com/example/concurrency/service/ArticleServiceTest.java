package com.example.concurrency.service;

import com.example.concurrency.domain.Article;
import com.example.concurrency.repository.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("낙관적락 테스트")
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void before() {
        Article article = new Article("제목수정", "내용수정");
        articleRepository.saveAndFlush(article);

    }
    @AfterEach
    public void after() {
        articleRepository.deleteAll();
    }

    @Test
    public void updateArticle() throws InterruptedException{
        // articleService.articleUpdate(1L, "", "");
        int numberOfThreads = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Future<?> future = executorService.submit(
                ()-> articleService.articleUpdate(1L, "", ""));

        Future<?> future2 = executorService.submit(
                ()-> articleService.articleUpdate(1L, "", ""));

        Future<?> future3 = executorService.submit(
                ()-> articleService.articleUpdate(1L, "", ""));

        Exception result = new Exception();

        try {
            future.get();
            future2.get();
            future3.get();

        }catch (ExecutionException e) {
            result = (Exception) e.getCause();
        }

        assertTrue(result instanceof OptimisticLockingFailureException);

    }



}