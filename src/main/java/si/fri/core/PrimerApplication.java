package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "primerApplication")

public class PrimerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
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
