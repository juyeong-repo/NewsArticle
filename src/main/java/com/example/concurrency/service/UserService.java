package com.example.concurrency.service;

import com.example.concurrency.domain.Author;
import com.example.concurrency.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    // 네임으로 가져올지 여기 고민됨
    public Author findAuthor (String name)  {
        Author author = authorRepository.findByName(name).orElseGet(()->{
            return new Author();
        });;
        return author;
    }


}
