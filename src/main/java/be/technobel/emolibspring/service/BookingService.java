package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<BookingEntity> create(BookingEntity bookingEntity);

    BookingEntity update(Integer id, BookingEntity bookingEntity);

    void delete(Integer id);

    List<BookingEntity> getAll();

    Optional<BookingEntity> findById(Integer id);
}
