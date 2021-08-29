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
public class UpdateBook {

    private final BookGateway gateway;
    private final FindBookById findBookById;

    public void execute(final Book updateBook) {
        final Long id = updateBook.getId();

        final Book book = findBookById.execute(id);

        if (!updateBook.getTitle().equals(book.getTitle())) {
            final Optional<Book> bookOptional = gateway.findByTitle(updateBook.getTitle());

            if (bookOptional.isPresent()) {
                log.error("Book already exists. Title: " + bookOptional.get().getTitle());
                throw new BusinessException("Book with name " + bookOptional.get().getTitle() + " already exists!");
            }
        }

        updateBook.setCreationTime(book.getCreationTime());

        gateway.update(updateBook);
        log.info("Book was updated with success");
    }

}
