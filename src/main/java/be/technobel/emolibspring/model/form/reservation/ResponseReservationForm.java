package be.technobel.emolibspring.model.form.reservation;

import be.technobel.emolibspring.model.dto.BookDTO;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;
import be.technobel.emolibspring.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.util.Date;

public class ResponseReservationForm {
    private Long id;

    private String name;

    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deadline;

//    private BookDTO book;
    private Object book;

    private Object user;

    public ResponseReservationForm(Long id, String name, String address, Date deadline, BookDTO book, Object user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.deadline = deadline;
        this.book = book;
        this.user = user;
    }

    public ResponseReservationForm(Long id, String name, String address, Date deadline, BookEntity bookEntity, UserEntity user) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Object getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

//    public static ResponseReservationForm toDto(ReservationEntity entity) {
//        return new ResponseReservationForm(
//                entity.getId(),
//                entity.getName(),
//                entity.getAddress(),
//                entity.getDeadline(),
//                entity.getBookId(),
//                entity.getUserId()
//        );
//    }
}
