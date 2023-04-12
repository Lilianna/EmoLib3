package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity create(UserEntity userEntity);

    UserEntity update(Long id, UserEntity userEntity);

    void delete(Long id);

    List<UserEntity> getAll();

    Optional<UserEntity> findById(Long id);

}
