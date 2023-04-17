package be.technobel.emolibspring.model.dto;

import be.technobel.emolibspring.model.entity.BookEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BorrowingDTO {
    private Long id;
    private Long borrowerId;
    private Long issuer;
    private Date createDate;
    private String receiver;
    private Date returnDate;
    private String fineAmount;
    private List<BookEntity> listBook = new ArrayList<BookEntity>();

    public BorrowingDTO(){}

    public BorrowingDTO(Long id, Long borrowerId, Long issuer, Date createDate, String receiver, Date returnDate, String fineAmount, List<BookEntity> listBook) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.issuer = issuer;
        this.createDate = createDate;
        this.receiver = receiver;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.listBook = listBook;
    }
}
