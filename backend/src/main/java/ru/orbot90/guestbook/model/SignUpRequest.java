package ru.orbot90.guestbook.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class SignUpRequest {
    @NotEmpty
    private String userName;
    @NotNull
    @Length(min = 6)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
