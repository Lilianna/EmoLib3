package be.technobel.emolibspring.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_donners")
public class BookDonnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "donner_id")
    private String donnerId;

    @Column(name = "type")
    private String type;
}
