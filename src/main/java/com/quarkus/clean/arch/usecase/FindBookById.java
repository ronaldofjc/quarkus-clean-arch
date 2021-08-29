package com.quarkus.clean.arch.usecase;

import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.exception.EntityNotFoundException;
import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindBookById {

    private final BookGateway gateway;

    public Book execute(final Long id) {
        return gateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Book with id {} not found on database", id);
                    throw new EntityNotFoundException("Book with id " + id + " not found on database");
                });
    }
}
