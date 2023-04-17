package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.helper.CommonService;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "authors")
@Entity
@Data
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<>();

    public AuthorEntity(String name) {
        this.name = name;
        this.slug= CommonService.convertStringToSlug(name);
    }

    public AuthorEntity() {

    }
}
