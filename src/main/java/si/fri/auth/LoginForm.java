package si.fri.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginForm implements Serializable {
    public String username;
    public String password;

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
