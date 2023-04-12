package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.constants.StatusEnum;
import be.technobel.emolibspring.converter.ConvertStatusCommonEnum;
import be.technobel.emolibspring.helper.CommonService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "books")
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

    @Column(name = "author")
    private String author;

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

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public BookEntity() {
    }

    public BookEntity(String ibsn, String title, String author, String subject, Date yearb, Integer pages, boolean copies, StatusEnum isIssued, Long categoryId) {
        this.ibsn = ibsn;
        this.title = title;
        this.slug = CommonService.convertStringToSlug(title);
        this.subject = subject;
        this.yearb = yearb;
        this.pages = pages;
        this.copies = copies;
        this.isIssued = isIssued;
        this.categoryId = categoryId;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIbsn() {
        return ibsn;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authorId) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getYearb() {
        return yearb;
    }

    public void setYearb(Date yearb) {
        this.yearb = yearb;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public boolean getCopies() {
        return copies;
    }

    public StatusEnum getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(StatusEnum isIssued) {
        this.isIssued = isIssued;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isCopies() {
        return copies;
    }

    public void setCopies(boolean copies) {
        this.copies = copies;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
