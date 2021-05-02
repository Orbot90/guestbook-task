package ru.orbot90.guestbook.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    private String roleName;

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public RoleEntity() {}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleName.equals(that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
