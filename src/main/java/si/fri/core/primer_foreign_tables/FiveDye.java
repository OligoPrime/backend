package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fiveDye")

public class FiveDye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( unique = true)
    private String fiveDye;

    @OneToMany
    @JoinColumn(name = "fiveDye_id")
    private Set<Primer> primers;

    public FiveDye() {
        // Jackson deserialization
    }

    public FiveDye(String fiveDye) {
        this.fiveDye = fiveDye;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getFiveDye() {
        return fiveDye;
    }

    public void setFiveDye(String fiveDye) {
        this.fiveDye = fiveDye;
    }
}
