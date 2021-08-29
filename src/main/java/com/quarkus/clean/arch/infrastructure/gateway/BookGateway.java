package com.quarkus.clean.arch.infrastructure.gateway;


import com.quarkus.clean.arch.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookGateway {
    void create(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();

    void update(Book book);

    void remove(Book book);

    Long count();
}