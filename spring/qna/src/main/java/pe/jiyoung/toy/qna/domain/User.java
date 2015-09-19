package pe.jiyoung.toy.qna.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

    @NotEmpty
    @Size(min = 2, max = 14)
    private String userId;

    @NotEmpty
    @Size(min = 2, max = 14)
    private String password;

    @NotEmpty
    private String name;

    @Email
    private String email;

    public User(){}
    public User(final String userId, final String password, final String name, final String email) {
        super();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [userId=" + this.userId + ", name=" + this.name + ", email=" + this.email + ", password=" + this.password + "]";
    }



}
