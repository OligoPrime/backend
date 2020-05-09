package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fiveModification")

public class FiveModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
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
