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
public class FindBookByTitle {

    private final BookGateway gateway;

    public Book execute(final String title) {
        return gateway.findByTitle(title)
                .orElseThrow(() -> {
                    log.error("Book with title {} not found on database", title);
                    throw new EntityNotFoundException("Book with title " + title + " not found on database");
                });
    }
}
