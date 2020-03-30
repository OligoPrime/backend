package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fiveModification")

public class FiveModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fiveModification;

    @OneToMany
    @JoinColumn(name = "fiveModification_id")
    private Set<Primer> primers;

    public FiveModification() {
        // Jackson deserialization
    }

    public FiveModification(String fiveModification) {
        this.fiveModification = fiveModification;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getFiveModification() {
        return fiveModification;
    }

    public void setFiveModification(String fiveModification) {
        this.fiveModification = fiveModification;
    }
}
