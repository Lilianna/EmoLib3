package be.technobel.emolibspring.service.impl;

import be.technobel.emolibspring.model.entity.BookingEntity;
import be.technobel.emolibspring.repository.BookingRepository;
import be.technobel.emolibspring.service.BookingService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("booking")
@DynamicUpdate
public class BookingServiceImpl implements BookingService {

    @Autowired
    final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Optional<BookingEntity> create(BookingEntity bookingEntity) {
        return this.findById(bookingRepository.save(bookingEntity).getId());
    }

    @Override
    public BookingEntity update(Integer id, BookingEntity bookingEntity) {
        BookingEntity bookingInfo = bookingRepository.getReferenceById(id);
        bookingEntity.setId(bookingInfo.getId());
        return bookingRepository.save(bookingEntity);
    }

    @Override
    public void delete(Integer id) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setId(id);
        this.bookingRepository.delete(bookingEntity);
    }

    @Override
    public List<BookingEntity> getAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<BookingEntity> findById(Integer id) {
        return bookingRepository.findById(id);
    }
}
