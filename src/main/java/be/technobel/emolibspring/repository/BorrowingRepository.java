package be.technobel.emolibspring.repository;

import be.technobel.emolibspring.model.entity.BorrowingEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long> {
    @Query(value = "SELECT * FROM borrowing WHERE user_id = :userId", nativeQuery = true)
    List<BorrowingEntity> getByUserId(Long userId);
}
