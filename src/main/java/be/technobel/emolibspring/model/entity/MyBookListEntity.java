package be.technobel.emolibspring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "myBooks")
public class MyBookListEntity {
    @Id
    private int id;
    private String title;
    private String author;
    private String isbn;

}
