package be.technobel.emolibspring.model.form.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCategoryForm {

    @NotBlank(message = "Category is required")
    @Size(max = 255, min = 1, message = "Category name range in 1 to 255")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
