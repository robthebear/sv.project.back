package fr.laposte.sv.project.back.dto;

import fr.laposte.sv.project.back.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor

public class HabilitationDto {
    String id;

    String motDePasse;

    List<Role> role;

    private HabilitationDto() {

    }

    public HabilitationDto(@NotNull String username) {
        this.id = username;
    }

    public HabilitationDto(@NotNull String username, List<Role> roleList) {
        this.id = username;
        this.role = roleList;
    }

}
