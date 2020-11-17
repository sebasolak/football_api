package footballapi.demo.model.userrole;

public class UserDto {

    private String login;
    private String email;
    private String password;

    public UserDto(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
