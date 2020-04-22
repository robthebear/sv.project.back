package fr.laposte.sv.project.back.dto;


public class JsonWebtoken {
    private final String token;

    public JsonWebtoken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
