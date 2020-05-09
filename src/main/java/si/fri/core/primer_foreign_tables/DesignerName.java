package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

//@Audited
@Entity
@Table(name = "designerName")

public class DesignerName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String designerName;

    @OneToMany
    @JoinColumn(name = "designerName_id")
    private Set<Primer> primers;

    public DesignerName() {
        // Jackson deserialization
    }

    public DesignerName(String designerName) {
        this.designerName = designerName;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }
}
