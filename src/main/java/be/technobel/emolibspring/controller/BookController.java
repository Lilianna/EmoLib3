package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.form.reservation.CreateBookForm;
import be.technobel.emolibspring.service.BookService;
import be.technobel.emolibspring.service.BookingService;
import be.technobel.emolibspring.service.impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }


    @GetMapping("")
    public ResponseEntity getListBook() {
        return Response.setResponse(true, HttpStatus.OK, bookService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity createBook(@RequestBody CreateBookForm createBookForm) {
        BookEntity bookEntity = new BookEntity(
                createBookForm.getIbsn(),
                createBookForm.getTitle(),
                createBookForm.getAuthorId(),
                createBookForm.getSubject(),
                createBookForm.getYearb(),
                createBookForm.getPages(),
                createBookForm.getCopies(),
                createBookForm.getIsIssued(),
                createBookForm.getCategoryId()
        );
        System.out.println(createBookForm.getAuthorId() +
                createBookForm.getCategoryId());
        Optional<BookEntity> bookingInfo = Optional.ofNullable(bookService.create(bookEntity));
        return Response.setResponse(true, HttpStatus.OK, bookingInfo);
    }

    @GetMapping("/{slug}")
    public ResponseEntity getDetail(@PathVariable String slug) {
        Optional<BookEntity> bookingInfo = bookService.getBookBySlug(slug);
        return Response.setResponse(true, HttpStatus.OK, bookingInfo);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity update(@PathVariable Long bookId, @RequestBody CreateBookForm createBookForm) {
        Optional<BookEntity> optional = bookService.findById(bookId);
        if (optional.isPresent()) {
            BookEntity bookingEntity = new BookEntity(
                    createBookForm.getIbsn(),
                    createBookForm.getTitle(),
                    createBookForm.getAuthorId(),
                    createBookForm.getSubject(),
                    createBookForm.getYearb(),
                    createBookForm.getPages(),
                    createBookForm.getCopies(),
                    createBookForm.getIsIssued(),
                    createBookForm.getCategoryId()
            );
            BookEntity bookingInfo = optional.get();
            bookService.update(bookingInfo.getId(), bookingEntity);
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
