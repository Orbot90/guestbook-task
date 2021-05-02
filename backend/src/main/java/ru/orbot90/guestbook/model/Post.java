package ru.orbot90.guestbook.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class Post {
    private Long id;
    private String userName;
    @NotEmpty
    @Length(min = 1, max = 1000)
    private String data;
    private String date;
    private String editedBy;
    private String editedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
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

    public String getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(String editedDate) {
        this.editedDate = editedDate;
    }

    public String getEditedBy() {
        return editedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                userName.equals(post.userName) &&
                data.equals(post.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, data);
    }
}
