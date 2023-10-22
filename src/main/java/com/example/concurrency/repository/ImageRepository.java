package com.example.concurrency.repository;

import com.example.concurrency.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
