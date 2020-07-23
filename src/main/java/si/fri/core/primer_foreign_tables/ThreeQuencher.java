package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "threeQuencher")

public class ThreeQuencher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( unique = true)
    private String threeQuencher;

    @OneToMany
    @JoinColumn(name = "threeQuencher_id")
    private Set<Primer> primers;

    public ThreeQuencher() {
        // Jackson deserialization
    }

    public ThreeQuencher(String threeQuencher) {
        this.threeQuencher = threeQuencher;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getThreeQuencher() {
        return threeQuencher;
    }

    public void setThreeQuencher(String threeQuencher) {
        this.threeQuencher = threeQuencher;
    }
}
