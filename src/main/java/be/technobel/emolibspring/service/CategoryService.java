package be.technobel.emolibspring.service;

import be.technobel.emolibspring.model.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryEntity create(CategoryEntity categoryEntity);

    CategoryEntity update(Long id, CategoryEntity categoryEntity);

    void delete(Long id);

    List<CategoryEntity> getAll();

    Optional<CategoryEntity> findById(Long id);

}
