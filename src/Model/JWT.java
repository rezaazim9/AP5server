package Model;

import java.io.Serial;
import java.io.Serializable;

public class JWT implements Serializable {
    @Serial
    final private static long serialVersionUID = -8445809092316972845L;
    private String token;
    private String username;
    public JWT(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
