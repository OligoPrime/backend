package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "box")

public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String box;

    @OneToMany
    @JoinColumn(name = "box_id")
    private Set<Primer> primers;

    public Box() {
        // Jackson deserialization
    }

    public Box(String box) {
        this.box = box;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }
}
