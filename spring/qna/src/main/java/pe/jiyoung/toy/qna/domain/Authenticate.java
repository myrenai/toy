package pe.jiyoung.toy.qna.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Authenticate {


    @NotEmpty
    @Size(min = 4, max = 14)
    private String userId;

    @NotEmpty
    @Size(min = 4, max = 14)
    private String password;

    public Authenticate() {
        super();
    }

    public Authenticate(final String userId, final String password) {
        super();
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Authenticate [userId=" + this.userId + ", password=" + this.password + "]";
    }

    public boolean matchPassword(final String password) {
        if(this.password == null) {
            return false;
        }
        return this.password.equals(password);
    }

}
