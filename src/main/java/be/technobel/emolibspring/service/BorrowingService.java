package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.BorrowingEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface BorrowingService {
    BorrowingEntity create(BorrowingEntity borrowingEntity);

//    BorrowingEntity update(Long id, BorrowingEntity borrowingEntity);

    void delete(Long id);

    List<BorrowingEntity> getAll();
}
