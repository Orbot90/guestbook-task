package ru.orbot90.guestbook.entities;

import javax.persistence.*;
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
    private Long userId;
    private String data;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return id.equals(that.id) &&
                userId.equals(that.userId) &&
                data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, data);
    }
}
