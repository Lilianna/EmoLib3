package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.helper.CommonService;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<BookEntity> bookEntitys;

    public void add(BookEntity bookEntity) {
        if(bookEntity!=null) {
            if (bookEntitys==null) {
                bookEntitys= new HashSet<>();
            }
        }

        bookEntitys.add(bookEntity);
        bookEntity.setCategory(this);
    }

    @Column(name = "date_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @PrePersist
    public void prePersist() {
        this.dateCreate = new Date();
    }
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
