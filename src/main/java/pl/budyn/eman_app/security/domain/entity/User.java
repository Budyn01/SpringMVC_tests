package pl.budyn.eman_app.security.domain.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.budyn.eman_app.model.entity.Photo;
import pl.budyn.eman_app.security.domain.Role;

import javax.persistence.*;
import java.util.*;

/**
 * Created by hlibe on 06.02.2017.
 */
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "enable", nullable = false)
    private boolean enable;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Collection<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private List<Photo> photo;

    static public String hashPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
    public User() {
        this.credentialsNonExpired = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
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

    public String getAuthoritiesNames(){
        StringBuilder aut = new StringBuilder("");
        authorities.forEach((a) -> aut.append(a.getRole().name()).append(","));
        return aut.deleteCharAt(aut.length() - 1).toString();
    }
    public String getStatus(){
        StringBuilder sb = new StringBuilder("");
        if(isAccountNonExpired()) sb.append("NonExpired").append(", ");
        if(isEnable()) sb.append("Enable").append(", ");
        if(isAccountNonLocked()) sb.append("NonLocked").append(", ");
        if(isCredentialsNonExpired()) sb.append("CredentialsNonExpired").append(", ");
        return sb.delete(sb.length() - 2, sb.length() - 1).toString();
    }

    public Collection<Photo> getPhotos() {
        return photo;
    }

    public void setPhotos(List photos) {
        this.photo = photos;
    }

    @Override
    public String toString() {
        StringBuilder aut = new StringBuilder("");
        authorities.forEach((a) -> aut.append(a.getRole().name()).append(","));
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                ", accountNonLocked=" + accountNonLocked +
                ", accountNonExpired=" + accountNonExpired +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", authorities=" + aut.deleteCharAt(aut.length() - 1).toString() +
                '}';
    }
}