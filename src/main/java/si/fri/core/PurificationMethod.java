package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purificationMethod")

public class PurificationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String purificationMethod;

    @OneToMany
    @JoinColumn(name = "purificationMethod_id")
    private Set<Primer> primers;

    public PurificationMethod() {
        // Jackson deserialization
    }

    public PurificationMethod(String purificationMethod) {
        this.purificationMethod = purificationMethod;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getPurificationMethod() {
        return purificationMethod;
    }

    public void setPurificationMethod(String purificationMethod) {
        this.purificationMethod = purificationMethod;
    }
}
