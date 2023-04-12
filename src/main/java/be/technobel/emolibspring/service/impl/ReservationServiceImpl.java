package be.technobel.emolibspring.service.impl;

import be.technobel.emolibspring.model.entity.ReservationEntity;
import be.technobel.emolibspring.repository.ReservationRepository;
import be.technobel.emolibspring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("reservation")
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationEntity create(ReservationEntity reservationEntity) {
        return reservationRepository.save(reservationEntity);
    }

    @Override
    public ReservationEntity update(Long id, ReservationEntity reservationEntity) {
        reservationEntity.setId(id);
        reservationEntity.setDeadline(reservationEntity.getDeadline());
        this.reservationRepository.save(reservationEntity);
        return reservationEntity;
    }

    @Override
    public void delete(Long id) {
        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationEntity> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<ReservationEntity> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<ReservationEntity> getReservationByUserId(Long userId) {
        return reservationRepository.getByUserId(userId);
    }
}
