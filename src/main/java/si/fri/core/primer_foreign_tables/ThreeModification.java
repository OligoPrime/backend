package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "threeModification")

public class ThreeModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String threeModification;

    @OneToMany
    @JoinColumn(name = "threeModification_id")
    private Set<Primer> primers;

    public ThreeModification() {
        // Jackson deserialization
    }

    public ThreeModification(String threeModification) {
        this.threeModification = threeModification;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getThreeModification() {
        return threeModification;
    }

    public void setThreeModification(String threeModification) {
        this.threeModification = threeModification;
    }
}
