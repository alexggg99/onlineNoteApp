package agashchuk.SystemSpecificPackage.model;


import org.hibernate.validator.constraints.NotEmpty;


public class AuthorizationRequest {
    @NotEmpty(message = "username cant be empty")
    private String username;
    @NotEmpty(message = "password cant be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
