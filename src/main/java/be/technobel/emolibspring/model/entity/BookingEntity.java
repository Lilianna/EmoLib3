package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.constants.StatusRoomEnum;
import be.technobel.emolibspring.converter.ConvertStatusEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_id")
    private Integer room_id;

    @Column(name = "owner_id")
    private Integer owner_id;

    @Column(name = "date_time")
    private Date date_time;
    @Column(name = "status")
    @Convert(converter = ConvertStatusEnum.class)
    private StatusRoomEnum status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public StatusRoomEnum getStatus() {
        return status;
    }

    public void setStatus(StatusRoomEnum status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}
