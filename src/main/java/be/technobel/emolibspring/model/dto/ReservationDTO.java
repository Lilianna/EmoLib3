package be.technobel.emolibspring.model.dto;

import be.technobel.emolibspring.model.entity.BookEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReservationDTO {

    private Long id;
    private String name;
    private String address;
    private Long userId;
    private BookEntity bookEntity;
    private Date createdAt;
    private Date updatedAt;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, String name, String address, Long userId, BookEntity bookEntity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.bookEntity = bookEntity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
