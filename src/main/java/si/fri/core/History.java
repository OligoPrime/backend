package si.fri.core;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.History.findAll",
                        query = "SELECT h FROM History h"
                )
        })
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    private String action;

    public History() {
        // Jackson deserialization
    }

    public History(User user, String action) {
        this.user = user;
        this.action = action;
        this.timestamp = new Date((new Date().getTime()));
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}