package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Entity
public class Habilitation implements Serializable {
    @Id
    String id;

    @NotNull
    @Column(nullable = false)
            @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String motDePasse;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    List<Role> roleList;

    public Habilitation() {
    }

    public Habilitation(String id, String encode, List<Role> roleList) {
        this.id = id;
        this.motDePasse = encode;
        this.roleList = roleList;
    }
}

