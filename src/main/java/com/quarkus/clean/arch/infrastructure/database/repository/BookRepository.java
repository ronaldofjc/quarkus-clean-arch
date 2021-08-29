package com.quarkus.clean.arch.infrastructure.database.repository;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import com.quarkus.clean.arch.infrastructure.database.model.BookModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookModel> {
    
    public Optional<BookModel> findByTitleOptional(String title) {
        return find("title", title).firstResultOptional();
      }
}
