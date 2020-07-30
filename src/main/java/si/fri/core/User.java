package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.Principal;
import java.util.HashSet;
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
                        query = "SELECT p FROM User p WHERE p.removed = false"
                ),
                @NamedQuery(
                        name = "si.fri.core.User.getForUsername",
                        query = "SELECT p FROM User p WHERE p.username = :username AND p.removed = false"
                )
        })
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private byte[] hash;
    private byte[] salt;
    private String role;

    private String name;
    private String workTitle;

    private boolean removed = false;

    @ElementCollection
    private Set<Long> favourites;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Primer> primers;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<History> history;

    public User(String username, String password, String role) {
        this.username = username;

        List<byte[]> salt_hash = hashPassword(password);
        Objects.requireNonNull(salt_hash);
        this.salt = salt_hash.get(0);
        this.hash = salt_hash.get(1);
        this.role = role;
        this.favourites = new HashSet<>();
    }

    public User(String username, String password, String role, String name, String workTitle) {
        this.username = username;
        this.name = name;
        this.workTitle = workTitle;
        List<byte[]> salt_hash = hashPassword(password);
        Objects.requireNonNull(salt_hash);
        this.salt = salt_hash.get(0);
        this.hash = salt_hash.get(1);
        this.role = role;
        this.favourites = new HashSet<>();
    }

    public User() {
    }

    public Set<Long> getFavourites() {
        return favourites;
    }

    public void setFavourites(Set<Long> linked) {
        this.favourites = linked;
    }

    public Set<History> getHistory() {
        return history;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public void setSalt(byte[] salt) {
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

    public byte[] getHash() {
        return this.hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}