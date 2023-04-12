package be.technobel.emolibspring.model.form.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateReservationForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Min value is 1")
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
