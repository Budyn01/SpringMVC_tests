package pl.budyn.eman_app.security.domain;


import pl.budyn.eman_app.security.domain.entity.User;

/**
 * Created by hlibe on 06.02.2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(),
              user.getPasswordHash(),
              user.isEnable(),
              user.isAccountNonExpired(),
              user.isCredentialsNonExpired(),
              user.isAccountNonLocked(),
              user.getAuthorities());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getAuthoritiesNames(){
        StringBuilder sb = new StringBuilder("");
        user.getAuthorities().forEach((authority) -> sb.append(authority.getRole().name()).append(","));
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public boolean hasAuthority(String role){
        return getAuthoritiesNames().contains(role.toUpperCase());
    }
    public boolean isAdmin(){
        return hasAuthority("ADMIN");
    }
    public boolean isUser(){
        return hasAuthority("USER");
    }
    public String getStatus(){
        return user.getStatus();
    }
}
