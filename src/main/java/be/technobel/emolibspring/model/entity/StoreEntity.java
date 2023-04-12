package be.technobel.emolibspring.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "stores")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "magasin_id")
    private Long magasinId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public StoreEntity(Long bookId, Long magasinId, Long amount) {
        this.bookId = bookId;
        this.magasinId = magasinId;
        this.amount = amount;
    }

    public StoreEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Long magasinId) {
        this.magasinId = magasinId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
