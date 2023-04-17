package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.constants.StatusEnum;
import be.technobel.emolibspring.converter.ConvertStatusCommonEnum;
import be.technobel.emolibspring.helper.CommonService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ibsn")
    private String ibsn;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "author_book",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<AuthorEntity> authors = new HashSet<>();

    @Column(name = "subject",columnDefinition="TEXT")
    private String subject;

    @Column(name = "yearb", columnDefinition = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date yearb;

    @Column(name = "pages", nullable = true)
    private Integer pages;

    @Column(name = "copies", nullable = true)
    private boolean copies;

    @Column(name = "is_issued", nullable = true)
    @Convert(converter = ConvertStatusCommonEnum.class)
    private StatusEnum isIssued;

//    Quan he
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public BookEntity() {
    }

    public BookEntity(String ibsn,
                      String title,
                      Set<AuthorEntity> authors,
                      String subject,
                      Date yearb,
                      Integer pages,
                      boolean copies,
                      StatusEnum isIssued,
                      CategoryEntity category) {
        this.ibsn = ibsn;
        this.title = title;
        this.slug = CommonService.convertStringToSlug(title);
        this.subject = subject;
        this.yearb = yearb;
        this.pages = pages;
        this.copies = copies;
        this.isIssued = isIssued;
        this.category=category;
        this.authors = authors;
    }
}
