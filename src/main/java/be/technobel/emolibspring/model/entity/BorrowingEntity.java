package be.technobel.emolibspring.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Data
@Table(name = "borrowings")
public class BorrowingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //người đặt
    @Column(name = "borrower_id")
    private Long borrowerId;

    @Column(name = "issuer")
    private Long issuer;

    @Column(name = "issue_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "fine_amount")
    private String fineAmount;

    @ElementCollection
    @Column(name="list_book")
    private List<Long> listBook = new ArrayList<Long>();

    public BorrowingEntity(){}

    public BorrowingEntity(Long borrowerId, Long issuer, String receiver, Date returnDate, String fineAmount, List<Long> listBook) {
        this.borrowerId = borrowerId;
        this.issuer = issuer;
        this.receiver = receiver;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.listBook = listBook;
    }
}
