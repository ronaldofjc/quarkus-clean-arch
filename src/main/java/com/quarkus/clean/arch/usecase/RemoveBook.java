package com.quarkus.clean.arch.usecase;

import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.exception.BusinessException;
import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Slf4j
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class RemoveBook {

    private final BookGateway gateway;
    private final FindBookById findBookById;

    public void execute(final Long id) {
        final Book book = findBookById.execute(id);

        if (!book.isActive()) {
            log.info("Book with id {} is already inactive", book.getId());
            throw new BusinessException("Book with id " + book.getId() + " is already inactive");
        }

        book.setActive(false);
        gateway.update(book);
        log.info("Book with title {} has been inactivated", book.getTitle());
    }
}
