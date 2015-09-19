package pe.jiyoung.toy.qna.domain;

public class User {

    private String userId;
    private String name;
    private String email;
    private String password;

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
