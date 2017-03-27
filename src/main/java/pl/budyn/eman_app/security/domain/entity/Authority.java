package pl.budyn.eman_app.security.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import pl.budyn.eman_app.security.domain.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlibe on 04.03.2017.
 */

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority() {
    }

    public Authority(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "role=" + role.name() +
                '}';
    }
}
