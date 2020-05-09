package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "designerPublication")

public class DesignerPublication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String designerPublication;

    @OneToMany
    @JoinColumn(name = "designerPublication_id")
    private Set<Primer> primers;

    public DesignerPublication() {
        // Jackson deserialization
    }

    public DesignerPublication(String designerPublication) {
        this.designerPublication = designerPublication;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getDesignerPublication() {
        return designerPublication;
    }

    public void setDesignerPublication(String designerPublication) {
        this.designerPublication = designerPublication;
    }
}
