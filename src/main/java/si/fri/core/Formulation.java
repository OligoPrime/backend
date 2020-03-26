package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "formulation")

public class Formulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String formulation;

    @OneToMany
    @JoinColumn(name = "formulation_id")
    private Set<Primer> primers;

    public Formulation() {
        // Jackson deserialization
    }

    public Formulation(String formulation) {
        this.formulation = formulation;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }
}
