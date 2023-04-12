package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity create(BookEntity bookEntity);

    BookEntity update(Long id, BookEntity bookEntity);

    void delete(Long id);

    List<BookEntity> getAll();

    Optional<BookEntity> findById(Long id);

    Optional<BookEntity> getBookBySlug(String slug);
}
