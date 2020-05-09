package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positionInReference")

public class PositionInReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String positionInReference;

    @OneToMany
    @JoinColumn(name = "positionInReference_id")
    private Set<Primer> primers;

    public PositionInReference() {
        // Jackson deserialization
    }

    public PositionInReference(String positionInReference) {
        this.positionInReference = positionInReference;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getPositionInReference() {
        return positionInReference;
    }

    public void setPositionInReference(String positionInReference) {
        this.positionInReference = positionInReference;
    }
}
