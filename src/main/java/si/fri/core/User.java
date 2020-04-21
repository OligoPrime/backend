package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static si.fri.auth.Passwords.hashPassword;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.User.findAll",
                        query = "SELECT p FROM User p"
                ),
                @NamedQuery(
                        name = "si.fri.core.User.getForUsername",
                        query = "SELECT p FROM User p WHERE p.username = :username"
                )
        })
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String username;

    private byte[] hash;
    private byte[] salt;
    private String role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Primer> primers;

    public User(String username, String password, String role) {
        this.username = username;

        List<byte []> salt_hash = hashPassword(password);
        Objects.requireNonNull(salt_hash);
        this.salt = salt_hash.get(0);
        this.hash = salt_hash.get(1);
        this.role = role;
    }

    public User() {
    }


    public  byte[] getSalt() {
        return this.salt;
    }

    public void setSalt( byte[] salt) {
        this.salt = salt;
    }

    public Set<Primer> getPrimers() {
        return primers;
    }

    public void setPrimers(Set<Primer> primers) {
        this.primers = primers;
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

    public byte[] getHash() {
        return this.hash;
    }

    public void setHash( byte[] hash) {
        this.hash = hash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}