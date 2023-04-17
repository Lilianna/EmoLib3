package be.technobel.emolibspring.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "author_book")
public class BookAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    // getters and setters
}
