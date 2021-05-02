package ru.orbot90.guestbook.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class Post {
    private Long id;
    private Long userId;
    @NotEmpty
    @Length(min = 1, max = 1000)
    private String data;
    private String date;
    private Long editedBy;
    private String editedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Long editedBy) {
        this.editedBy = editedBy;
    }

    public String getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(String editedDate) {
        this.editedDate = editedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                userId.equals(post.userId) &&
                data.equals(post.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, data);
    }
}
