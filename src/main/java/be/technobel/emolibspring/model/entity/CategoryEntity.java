package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.helper.CommonService;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public CategoryEntity(String name) {
        this.name = name;
        this.slug = CommonService.convertStringToSlug(name);
    }

    public CategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


}
