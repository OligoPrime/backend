package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "designerDatabase")

public class DesignerDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String designerDatabase;

    @OneToMany
    @JoinColumn(name = "designerDatabase_id")
    private Set<Primer> primers;

    public DesignerDatabase() {
        // Jackson deserialization
    }

    public DesignerDatabase(String designerDatabase) {
        this.designerDatabase = designerDatabase;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getDesignerDatabase() {
        return designerDatabase;
    }

    public void setDesignerDatabase(String designerDatabase) {
        this.designerDatabase = designerDatabase;
    }
}
