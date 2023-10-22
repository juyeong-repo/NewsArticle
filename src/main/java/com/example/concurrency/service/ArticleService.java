package com.example.concurrency.service;

import com.example.concurrency.domain.Article;
import com.example.concurrency.domain.Author;
import com.example.concurrency.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 아티클 작성
   @Transactional
    public void articleWrite(Article article, Author author) {
        article.setView(0);
        article.setAuthor(author);
        articleRepository.save(article);
    }

    // 페이징
    @Transactional(readOnly = true)
    public Page<Article> articleList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    // 아티클 보기
    @Transactional(readOnly = true)
    public Article articleDetail(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(()->{
            return new IllegalArgumentException("아티클 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });

    }

    //아티클 수정
    @Transactional
    public void articleUpdate(Long articleId, String title, String content) { //requestArticle
        Article article = articleRepository.findById(articleId).orElseThrow(()->{
            return new IllegalArgumentException("아티클 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        article.setTitle(title);
        article.setContent(content);

        //throw 익셉션 여기서 던질까?

        articleRepository.save(article);

    } // 해당 함수로 종료시 트랜잭션이 종료, 이때 더티체킹 - 자동 업데이트 됨. db flush




}
