package be.technobel.emolibspring.service.impl;

import be.technobel.emolibspring.model.entity.CategoryEntity;
import be.technobel.emolibspring.repository.CategoryRepository;
import be.technobel.emolibspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("category")
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(Long id, CategoryEntity categoryEntity) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        if (entity.isPresent()) {
            entity.get().setName(categoryEntity.getName());
            entity.get().setSlug(categoryEntity.getSlug());
            return categoryRepository.save(entity.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt" ));
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
