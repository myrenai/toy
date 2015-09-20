package pe.jiyoung.toy.qna.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

    @NotEmpty
    @Size(min = 4, max = 14)
    private String userId;

    @NotEmpty
    @Size(min = 4, max = 14)
    private String password;

    @NotEmpty
    private String name;

    @Email
    private String email;

    public User(){}
    public User(final String userId){
        this.userId = userId;
    }
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

    public boolean matchPassword(final Authenticate authenticate) {
        if(this.password == null) {
            return false;
        }

        return authenticate.matchPassword(this.password);
    }

    public boolean matchUserId(final String userId) {
        if(userId == null) {
            return false;
        }
        return (userId.equals(this.userId));
    }
    public User update(final User updateUser) {
        if(!this.matchUserId(updateUser.userId)){
            throw new IllegalArgumentException();
        }
        return new User(this.userId, updateUser.password, updateUser.name, updateUser.email);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!this.email.equals(other.email)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!this.userId.equals(other.userId)) {
            return false;
        }
        return true;
    }





}
