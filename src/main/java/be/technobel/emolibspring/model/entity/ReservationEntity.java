package be.technobel.emolibspring.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;


    public ReservationEntity(String name, String address, Long userId, Long bookId) {
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.bookId = bookId;
    }

    public ReservationEntity() {
    }

}
