package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.entity.CategoryEntity;
import be.technobel.emolibspring.model.form.reservation.CreateCategoryForm;
import be.technobel.emolibspring.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity getListBooking() {
        return Response.setResponse(true, HttpStatus.OK, categoryServiceImpl.getAll());
    }

    @PostMapping("/create")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity createBooking(@RequestBody @Valid CreateCategoryForm categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity(
                categoryDTO.getName()
        );
        CategoryEntity categoryInfo = categoryServiceImpl.create(categoryEntity);
        return Response.setResponse(true, HttpStatus.OK, categoryInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBooking(@PathVariable Long id) {
        return Response.setResponse(true, HttpStatus.OK, categoryServiceImpl.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBooking(@PathVariable Long id, @RequestBody @Valid CreateCategoryForm categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity(
                categoryDTO.getName()
        );
        CategoryEntity categoryInfo = categoryServiceImpl.update(id, categoryEntity);
        return Response.setResponse(true, HttpStatus.OK, categoryInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        categoryServiceImpl.delete(id);
        return Response.setResponse(true, HttpStatus.OK, null);
    }
}
