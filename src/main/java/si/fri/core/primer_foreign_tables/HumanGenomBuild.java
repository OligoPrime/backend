package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "humanGenomBuild")

public class HumanGenomBuild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String humanGenomBuild;

    @OneToMany
    @JoinColumn(name = "humanGenomBuild_id")
    private Set<Primer> primers;

    public HumanGenomBuild() {
        // Jackson deserialization
    }

    public HumanGenomBuild(String humanGenomBuild) {
        this.humanGenomBuild = humanGenomBuild;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getHumanGenomBuild() {
        return humanGenomBuild;
    }

    public void setHumanGenomBuild(String humanGenomBuild) {
        this.humanGenomBuild = humanGenomBuild;
    }
}
