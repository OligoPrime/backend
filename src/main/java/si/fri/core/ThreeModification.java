package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "threeModification")

public class ThreeModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
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
