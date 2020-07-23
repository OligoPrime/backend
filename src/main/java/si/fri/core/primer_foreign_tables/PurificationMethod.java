package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purificationMethod")

public class PurificationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( unique = true)
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
