package be.technobel.emolibspring.model.entity;

import be.technobel.emolibspring.constants.StatusEnum;
import be.technobel.emolibspring.converter.ConvertStatusCommonEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(unique = true, name = "email")
    private String email;
    @Column(name = "status", columnDefinition = "TINYINT(4) DEFAULT 1 NOT NULL")
    @Convert(converter = ConvertStatusCommonEnum.class)
    private StatusEnum status;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Column(name = "created_at", columnDefinition = "datetime")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private Date updatedAt;

    public UserEntity() {

    }

    public void User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = StatusEnum.ACTIVE;
    }
}
