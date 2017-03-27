package pl.budyn.eman_app.security.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.budyn.eman_app.security.domain.entity.Authority;
import pl.budyn.eman_app.security.service.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hlibe on 06.02.2017.
 */
@Component
public class UserCreateForm {


    private String email;
    private String password;
    private String passwordRepeated;
    private String surname;
    private String name;
    private Collection<Authority> authorities = new ArrayList<Authority>() {{add(new Authority(Role.USER));}};

    public UserCreateForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority){
        authorities.add(authority);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        authorities.forEach((a) -> sb.append(a.getRole().name()).append(","));
        return "UserCreateForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordRepeated='" + passwordRepeated + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", authorities='" + sb.deleteCharAt(sb.length() - 1).toString() + '\'' +
                '}';
    }
}
