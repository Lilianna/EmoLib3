package be.technobel.emolibspring.service.impl;


import be.technobel.emolibspring.model.entity.BorrowingEntity;
import be.technobel.emolibspring.repository.BorrowingRepository;
import be.technobel.emolibspring.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("Borrowing")
@Service
public class BorrowingServiceImpl implements BorrowingService {
    @Autowired
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository) {
        this.borrowingRepository=borrowingRepository;
    }

    @Override
    public BorrowingEntity create(BorrowingEntity borrowingEntity) {
        return borrowingRepository.save(borrowingEntity);
    }
//    @Override
//    public BorrowingEntity update(Long id, BorrowingEntity borrowingEntity){
//
//    }
    @Override
    public void delete(Long id){
        borrowingRepository.deleteById(id);
    }
    @Override
    public List<BorrowingEntity> getAll(){
        return borrowingRepository.findAll();
    }

    public List<BorrowingEntity> getByIdUser(Long id){
        return borrowingRepository.getByUserId(id);
    }
}
