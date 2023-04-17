package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.entity.AuthorEntity;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.entity.CategoryEntity;
import be.technobel.emolibspring.model.form.reservation.CreateBookForm;
import be.technobel.emolibspring.repository.AuthorRepository;
import be.technobel.emolibspring.service.BookService;
import be.technobel.emolibspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;

    @GetMapping("")
    public ResponseEntity getListBook() {
        return Response.setResponse(true, HttpStatus.OK, bookService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity createBook(@RequestBody CreateBookForm createBookForm) {
        Optional<CategoryEntity> category = categoryService.findById(createBookForm.getCategoryId());
        if(!category.isPresent()) {
            return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
        }
        BookEntity bookEntity = new BookEntity(
                createBookForm.getIbsn(),
                createBookForm.getTitle(),
                new HashSet<>(),
                createBookForm.getSubject(),
                createBookForm.getYearb(),
                createBookForm.getPages(),
                createBookForm.getCopies(),
                createBookForm.getIsIssued(),
                category.get()
        );
        for (Long id : createBookForm.getAuthors()) {
            AuthorEntity author = authorRepository.findById(id).orElseThrow();
            bookEntity.getAuthors().add(author);
        }
        Optional<BookEntity> bookingInfo = Optional.ofNullable(bookService.create(bookEntity));
        return Response.setResponse(true, HttpStatus.OK, bookingInfo);
    }

    @GetMapping("/myBooks")
    public String getMyBooks(){
        return "myBooks";
    }
    @GetMapping("/{slug}")
    public ResponseEntity getDetail(@PathVariable String slug) {
        Optional<BookEntity> bookingInfo = bookService.getBookBySlug(slug);
        return Response.setResponse(true, HttpStatus.OK, bookingInfo);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity update(@PathVariable Long bookId, @RequestBody CreateBookForm createBookForm) {
        Optional<CategoryEntity> category = categoryService.findById(createBookForm.getCategoryId());
        Optional<BookEntity> optional = bookService.findById(bookId);
        if (optional.isPresent() && category.isPresent()) {
            BookEntity bookEntity = new BookEntity(
                    createBookForm.getIbsn(),
                    createBookForm.getTitle(),
                    new HashSet<>(),
                    createBookForm.getSubject(),
                    createBookForm.getYearb(),
                    createBookForm.getPages(),
                    createBookForm.getCopies(),
                    createBookForm.getIsIssued(),
                    category.get()
            );
            for (Long id : createBookForm.getAuthors()) {
                AuthorEntity author = authorRepository.findById(id).orElseThrow();
                bookEntity.getAuthors().add(author);
            }
            BookEntity bookingInfo = optional.get();
            bookService.update(bookingInfo.getId(), bookEntity);
            return Response.setResponse(true, HttpStatus.OK, bookingInfo);
        }
        return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return Response.setResponse(true, HttpStatus.OK, null);
    }
}
