package si.fri.core.primer_foreign_tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.fri.core.Primer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String project;

    @OneToMany
    @JoinColumn(name = "project_id")
    private Set<Primer> primers;

    public Project() {
        // Jackson deserialization
    }

    public Project(String project) {
        this.project = project;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}

