package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ncbiGenId")

public class NcbiGenId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String ncbiGenId;

    @OneToMany
    @JoinColumn(name = "ncbiGenId_id")
    private Set<Primer> primers;

    public NcbiGenId() {
        // Jackson deserialization
    }

    public NcbiGenId(String ncbiGenId) {
        this.ncbiGenId = ncbiGenId;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getNcbiGenId() {
        return ncbiGenId;
    }

    public void setNcbiGenId(String ncbiGenId) {
        this.ncbiGenId = ncbiGenId;
    }
}
