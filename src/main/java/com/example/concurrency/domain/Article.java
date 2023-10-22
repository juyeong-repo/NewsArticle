package com.example.concurrency.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Article {
    // thumbnail은 일단x
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleId; //글번호

    @Column(nullable = false, length = 100)
    private String title; //제목

    private String categoryName; //카테고리 이름

    private int categoryId; //카테고리 아이디

    @Column(name= "status")
    @Enumerated(EnumType.STRING)
    private Status status; //아티클 상태

    @Lob
    private String content; //본문

    private int view; //조회수

    @CreationTimestamp
    private LocalDateTime published; //작성일

    @CreationTimestamp
    private LocalDateTime updated; //최종수정일

    private boolean isPublic; //공개여부

    private boolean isMemberOnly; //멤버쉽 여부

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="authorId")
    private Author author;


    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateArticle(Long articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    @Version
    private Long version;

}
