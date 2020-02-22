package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "hello")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.Hello.findAll",
                        query = "SELECT p FROM Hello p"
                )
        })
public class Hello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    public Hello() {
        // Jackson deserialization
    }

    public Hello(String content) {
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}