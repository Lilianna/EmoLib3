package be.technobel.emolibspring.model.form.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReservationForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Min value is 1")
    private Long idbook;

}
