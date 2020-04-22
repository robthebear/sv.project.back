package fr.laposte.sv.project.back.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_UTILISATEUR,
    ROLE_ADMIN,
    ROLE_SUPERADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
