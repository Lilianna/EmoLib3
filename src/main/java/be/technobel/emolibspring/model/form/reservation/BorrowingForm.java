package be.technobel.emolibspring.model.form.reservation;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BorrowingForm {
    private Long issuer;
    private String receiver;
    private Date returnDate;
    private String fineAmount;
    private List<Long> listBook = new ArrayList<Long>();
}
