package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationEntity create(ReservationEntity userEntity);

    ReservationEntity update(Long id, ReservationEntity userEntity);

    void delete(Long id);

    List<ReservationEntity> getAll();

    Optional<ReservationEntity> findById(Long id);

}
