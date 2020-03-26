package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organism")

public class Organism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String organism;

    @OneToMany
    @JoinColumn(name = "organism_id")
    private Set<Primer> primers;

    public Organism() {
        // Jackson deserialization
    }

    public Organism(String organism) {
        this.organism = organism;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }
}
