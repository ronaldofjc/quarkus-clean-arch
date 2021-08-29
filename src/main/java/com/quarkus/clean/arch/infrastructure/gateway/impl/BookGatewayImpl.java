package com.quarkus.clean.arch.infrastructure.gateway.impl;

import com.quarkus.clean.arch.adapter.BookAdapter;
import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.exception.GatewayException;
import com.quarkus.clean.arch.infrastructure.database.model.BookModel;
import com.quarkus.clean.arch.infrastructure.database.repository.BookRepository;
import com.quarkus.clean.arch.infrastructure.gateway.BookGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class BookGatewayImpl implements BookGateway {

    private final BookRepository repository;
    private final EntityManager entityManager;

    @Override
    public void create(final Book book) {
        final BookModel bookModel = BookAdapter.fromDomainToModel(book);

        try {
            entityManager.persist(bookModel);
        } catch (final Exception e) {
            log.error("Error when trying to save new book {} on database. Stack: {}", book.getTitle(), e);
            throw new GatewayException("Error when trying to save new book on database" + e);
        }
    }

    @Override
    public Optional<Book> findById(final Long id) {
        final Optional<BookModel> bookModel;

        try {
            bookModel = repository.findByIdOptional(id);
        } catch (final Exception e) {
            log.error("Error when trying to find book by id {} on database. Stack: {}", id, e);
            throw new GatewayException("Error when trying to find book by id on database" + e);
        }

        return bookModel.map(BookAdapter::fromModelToDomain);
    }

    @Override
    public Optional<Book> findByTitle(final String title) {
        final Optional<BookModel> bookModel;

        try {
            bookModel = repository.findByTitleOptional(title);
        } catch (final Exception e) {
            log.error("Error when trying to find book {} on database. Stack: {}", title, e);
            throw new GatewayException("Error when trying to find book on database" + e);
        }

        return bookModel.map(BookAdapter::fromModelToDomain);
    }

    @Override
    public List<Book> findAll() {
        final List<BookModel> books = new ArrayList<>();
        final List<Book> bookList = new ArrayList<>();

        try {
            repository.list("active", true).stream().forEach(books::add);
            //repository.findAll().stream().forEach(books::add);
        } catch (final Exception e) {
            log.error("Error when trying to find all books on database. Stack: " + e);
            throw new GatewayException("Error when trying to find all books on database" + e);
        }

        books.forEach(book -> bookList.add(BookAdapter.fromModelToDomain(book)));
        return bookList;
    }

    @Override
    public void update(final Book book) {
        final BookModel bookModel = BookAdapter.fromDomainToModelForUpdate(book);

        try {
            entityManager.merge(bookModel);
        } catch (final Exception e) {
            log.error("Error when trying to update book {} on database. Stack: {}", book.getTitle(), e);
            throw new GatewayException("Error when trying to update book on database" + e);
        }
    }

    @Override
    public void remove(final Book book) {
        final BookModel bookModel = BookAdapter.fromDomainToModelForUpdate(book);

        try {
            entityManager.merge(bookModel);
        } catch (final Exception e) {
            log.error("Error when trying to update book {} on database. Stack: {}", book.getTitle(), e);
            throw new GatewayException("Error when trying to update book on database" + e);
        }
    }

    @Override
    public Long count() {
        try {
            return repository.count();
        } catch (final Exception e) {
            log.error("Error when trying to count books on database. Stack: " + e);
            throw new GatewayException("Error when trying to count books on database" + e);
        }
    }
}
