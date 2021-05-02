package ru.orbot90.guestbook.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private UserEntity user;
    @NotNull
    private String data;
    @NotNull
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "edited_by")
    private UserEntity editedBy;
    private LocalDateTime editedDate;
    private boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserEntity getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(UserEntity editedBy) {
        this.editedBy = editedBy;
    }

    public LocalDateTime getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(LocalDateTime editedDate) {
        this.editedDate = editedDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return id.equals(that.id) &&
                user.getId().equals(that.user.getId()) &&
                data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), data);
    }
}
