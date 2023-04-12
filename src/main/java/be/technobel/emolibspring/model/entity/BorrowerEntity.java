package be.technobel.emolibspring.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowers")
public class BorrowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
