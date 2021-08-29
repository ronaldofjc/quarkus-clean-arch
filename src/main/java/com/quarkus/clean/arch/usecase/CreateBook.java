package com.quarkus.clean.arch.usecase;

import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.exception.BusinessException;
import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class CreateBook {

    private final BookGateway gateway;

    public void execute(final Book book) {
        final Optional<Book> bookOptional = gateway.findByTitle(book.getTitle());

        if (bookOptional.isPresent()) {
            log.error("Book already exists. Title: " + bookOptional.get().getTitle());
            throw new BusinessException("Book with name " + bookOptional.get().getTitle() + " already exists!");
        }

        gateway.create(book);
        log.info("Book was created with success");
    }

}
