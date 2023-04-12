package be.technobel.emolibspring.model.form.reservation;


import be.technobel.emolibspring.constants.StatusEnum;
import be.technobel.emolibspring.converter.ConvertStatusCommonEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CreateBookForm {

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Subject is required")
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "Author is required")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "Category is required")
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "copies")
    private boolean copies;


    @NotBlank(message = "IBSN is required")
    @Column(name = "ibsn")
    private String ibsn;

    @Column(name = "is_issued")
    @Convert(converter = ConvertStatusCommonEnum.class)
    private StatusEnum isIssued;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "yearb")
    private Date yearb;

    public CreateBookForm(String title, String subject, String author, Long categoryId, boolean copies, String ibsn, Integer pages, Date yearb) {
        this.title = title;
        this.subject = subject;
        this.author = author;
        this.categoryId = categoryId;
        this.copies = copies;
        this.ibsn = ibsn;
        this.isIssued = StatusEnum.ACTIVE;
        this.pages = pages;
        this.yearb = yearb;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getAuthorId() {
        return author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public boolean getCopies() {
        return copies;
    }

    public String getIbsn() {
        return ibsn;
    }

    public StatusEnum getIsIssued() {
        return isIssued;
    }

    public Integer getPages() {
        return pages;
    }

    public Date getYearb() {
        return yearb;
    }

    public boolean isCopies() {
        return copies;
    }
}
