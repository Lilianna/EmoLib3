package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.AuthorEntity;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<AuthorEntity> create(AuthorEntity authorEntity);

    AuthorEntity update(Long id, AuthorEntity authorEntity);

    void delete(Long id);

    List<AuthorEntity> getAll();

    Optional<AuthorEntity> findById(Long id);
}
