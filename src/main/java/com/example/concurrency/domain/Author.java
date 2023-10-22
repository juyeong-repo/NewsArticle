package com.example.concurrency.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int writerId;

    private String name;

    @Column(nullable = false, length = 100)
    private String profile; //프로필

    private String profileImageUrl;

    @OneToOne(mappedBy = "author", fetch = FetchType.LAZY)
    private Image profileImage;



}
