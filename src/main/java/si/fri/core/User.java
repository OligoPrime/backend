package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.Principal;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.User.findAll",
                        query = "SELECT p FROM User p"
                )
        })
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Primer> primers;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String content) {
        this.username = content;
    }

    @Override
    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}