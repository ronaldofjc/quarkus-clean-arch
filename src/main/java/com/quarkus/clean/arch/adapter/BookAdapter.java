package com.quarkus.clean.arch.adapter;

import com.quarkus.clean.arch.domain.Book;
import com.quarkus.clean.arch.endpoint.entity.BookResponse;
import com.quarkus.clean.arch.endpoint.entity.CreateBookRequest;
import com.quarkus.clean.arch.endpoint.entity.UpdateBookRequest;
import com.quarkus.clean.arch.infrastructure.database.model.BookModel;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter {

    private BookAdapter() {
        throw new IllegalStateException("Utility class");
    }

    public static BookModel fromDomainToModel(final Book source) {
        return BookModel.builder()
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .active(true)
                .creationTime(ZonedDateTime.now())
                .updateTime(ZonedDateTime.now())
                .build();
    }

    public static BookModel fromDomainToModelForUpdate(final Book source) {
        return BookModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .active(source.isActive())
                .creationTime(source.getCreationTime())
                .updateTime(ZonedDateTime.now())
                .build();
    }

    public static Book fromModelToDomain(final BookModel source) {
        return Book.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .active(source.isActive())
                .creationTime(source.getCreationTime())
                .updateTime(source.getUpdateTime())
                .build();
    }

    public static BookResponse fromDomainToResponse(final Book source) {
        return BookResponse.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .active(source.isActive())
                .creationTime(source.getCreationTime())
                .updateTime(source.getUpdateTime())
                .build();
    }

    public static List<BookResponse> fromDomainListToResponseList(final List<Book> books) {
        List<BookResponse> bookResponseList = new ArrayList<>();

        books.forEach(book -> bookResponseList.add(BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pages(book.getPages())
                .active(book.isActive())
                .creationTime(book.getCreationTime())
                .updateTime(book.getUpdateTime())
                .build()));

        return bookResponseList;
    }

    public static Book fromVMToDomain(final CreateBookRequest source) {
        return Book.builder()
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .build();
    }

    public static Book fromVMToDomainForUpdate(final UpdateBookRequest source) {
        return Book.builder()
                .id(source.getId())
                .title(source.getTitle())
                .author(source.getAuthor())
                .pages(source.getPages())
                .active(source.isActive())
                .build();
    }
}
