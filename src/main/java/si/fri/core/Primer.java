package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "primers")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.Primer.findAll",
                        query = "SELECT p FROM Primer p"
                )
        })
public class Primer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public Primer() {
        // Jackson deserialization
    }

    public Primer(String name) {
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String name) {
        this.name = name;
    }
}