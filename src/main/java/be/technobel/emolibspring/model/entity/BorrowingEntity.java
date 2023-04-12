package be.technobel.emolibspring.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "borrowings")
public class BorrowingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "borrower_id")
    private int borrowerId;

    @Column(name = "issuer")
    private int issuer;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "fine_amount")
    private String fineAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getIssuer() {
        return issuer;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

}
