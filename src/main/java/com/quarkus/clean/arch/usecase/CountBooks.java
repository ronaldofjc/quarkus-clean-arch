package com.quarkus.clean.arch.usecase;

import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CountBooks {

    private final BookGateway gateway;

    public Long execute() {
        Long countBooks = gateway.count();
        log.info("Found {} books on database", countBooks);
        return countBooks;
    }
}
