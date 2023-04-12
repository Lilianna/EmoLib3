package be.technobel.emolibspring.service.impl;

import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.repository.BookRepository;
import be.technobel.emolibspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("books")
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity create(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity update(Long id, BookEntity bookEntity) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isPresent()) {
            entity.get().setIbsn(bookEntity.getIbsn());
            entity.get().setTitle(bookEntity.getTitle());
            entity.get().setAuthor(bookEntity.getAuthor());
            entity.get().setSubject(bookEntity.getSubject());
            entity.get().setYearb(bookEntity.getYearb());
            entity.get().setPages(bookEntity.getPages());
            entity.get().setCopies(bookEntity.isCopies());
            entity.get().setIsIssued(bookEntity.getIsIssued());
            entity.get().setCategoryId(bookEntity.getCategoryId());
            System.out.println(bookEntity.getAuthor());
            return bookRepository.save(entity.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> getAll() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }


    public Optional<BookEntity> getBookBySlug(String slug) {
        return bookRepository.findBySlug(slug);
    }
}
