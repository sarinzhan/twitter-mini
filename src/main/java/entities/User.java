package entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String login;
    private String password;
    private LocalDate registerDate;
    private UserType userType; //тип пользователя

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public User setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(registerDate, user.registerDate) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, registerDate, userType);
    }
}
