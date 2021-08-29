package com.quarkus.clean.arch.usecase;

import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindAllBooks {

    private final BookGateway gateway;

    public List<Book> execute() {
        return gateway.findAll();
    }

}
