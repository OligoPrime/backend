package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

//@Audited
@Entity
@Table(name = "freezer")

public class Freezer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String freezer;

    @OneToMany
    @JoinColumn(name = "freezer_id")
    private Set<Primer> primers;

    public Freezer() {
        // Jackson deserialization
    }

    public Freezer(String freezer) {
        this.freezer = freezer;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getFreezer() {
        return freezer;
    }

    public void setFreezer(String freezer) {
        this.freezer = freezer;
    }
}
