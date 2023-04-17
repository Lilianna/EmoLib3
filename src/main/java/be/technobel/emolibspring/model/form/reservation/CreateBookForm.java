package be.technobel.emolibspring.model.form.reservation;


import be.technobel.emolibspring.constants.StatusEnum;
import be.technobel.emolibspring.converter.ConvertStatusCommonEnum;
import be.technobel.emolibspring.model.entity.AuthorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class CreateBookForm {

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Subject is required")
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "Author is required")
    @Column(name = "author")
    private Set<Long> authors;

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

    public boolean getCopies() {
        return copies;
    }
}
