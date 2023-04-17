package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.model.entity.AuthorEntity;
import be.technobel.emolibspring.model.form.reservation.AuthorForm;
import be.technobel.emolibspring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@CrossOrigin
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public AuthorEntity create(@RequestBody AuthorForm authorForm) {
        AuthorEntity author = new AuthorEntity(authorForm.getName());

        return authorService.create(author).get();
    }
}
