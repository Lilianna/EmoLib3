package be.technobel.emolibspring.service.impl;

import be.technobel.emolibspring.model.entity.AuthorEntity;
import be.technobel.emolibspring.repository.AuthorRepository;
import be.technobel.emolibspring.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("author")
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    @Override
    public Optional<AuthorEntity> create(AuthorEntity authorEntity) {
        return Optional.of(authorRepository.save(authorEntity));
    }

    @Override
    public AuthorEntity update(Long id, AuthorEntity authorEntity) {
        AuthorEntity author = authorRepository.getReferenceById(id);
        authorEntity.setId(author.getId());
        return authorRepository.save(authorEntity);
    }

    @Override
    public void delete(Long id){
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorEntity> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return authorRepository.findById(id);
    }

}
