package fr.laposte.sv.project.back.dto;

/**
 * model du token
 */
public class JsonWebtoken {
    private final String access_token;

    public JsonWebtoken(String token) {
        this.access_token = token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
