package si.fri.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufacturer")

public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String manufacturer;

    @OneToMany
    @JoinColumn(name = "manufacturer_id")
    private Set<Primer> primers;

    public Manufacturer() {
        // Jackson deserialization
    }

    public Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
