package be.technobel.emolibspring.repository;

import be.technobel.emolibspring.model.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
}
