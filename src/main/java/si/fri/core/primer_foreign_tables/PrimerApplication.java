package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

//@Audited
@Entity
@Table(name = "primerApplication")

public class PrimerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String primerApplication;

    @OneToMany
    @JoinColumn(name = "primerApplication_id")
    private Set<Primer> primers;

    public PrimerApplication() {
        // Jackson deserialization
    }

    public PrimerApplication(String primerApplication) {
        this.primerApplication = primerApplication;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getPrimerApplication() {
        return primerApplication;
    }

    public void setPrimerApplication(String primerApplication) {
        this.primerApplication = primerApplication;
    }
}
