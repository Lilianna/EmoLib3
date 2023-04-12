package be.technobel.emolibspring.repository;

import be.technobel.emolibspring.model.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    @Query(value = "SELECT * FROM reservations WHERE user_id = :userId",
            nativeQuery = true)
    List<ReservationEntity> getByUserId(Long userId);
}
