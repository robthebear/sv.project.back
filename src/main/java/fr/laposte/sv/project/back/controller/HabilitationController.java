package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.dto.HabilitationDto;
import fr.laposte.sv.project.back.dto.JsonWebtoken;
import fr.laposte.sv.project.back.exception.ExistingUserNameException;
import fr.laposte.sv.project.back.exception.InvalidCredentialsException;
import fr.laposte.sv.project.back.model.Habilitation;
import fr.laposte.sv.project.back.repository.HabilitationRepository;
import fr.laposte.sv.project.back.service.HabilitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habilitation")
public class HabilitationController {

    @Autowired
    private HabilitationService habilitationService;

    @Autowired
    private HabilitationRepository habilitationRepository;


    /**
     * Methode pour enregistrer un nouvel utilisateur dans la BD.
     *
     * @param user utiliateur.
     * @return un JWT si la connection est OK sinon une mauvaise réponse
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Habilitation> signUp(@RequestBody Habilitation user) {
        try {
            return ResponseEntity.ok(habilitationService.signup(user));
        } catch (ExistingUserNameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Methode pour que l'utilisateur se connecte
     * @param user
     * @return le token avec le role et id RH
     */
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebtoken> signIn(@RequestBody Habilitation user) {
//        System.out.println("Coucou");
        try {
            return ResponseEntity.ok(new JsonWebtoken(habilitationService.signin(user.getId(), user.getMotDePasse())));
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<HabilitationDto> getAllUsers() {
        return habilitationService.findAllUsers().stream().map(habilitation -> new HabilitationDto(habilitation.getId())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HabilitationDto> getOneUser(@PathVariable String id) {
        Optional<Habilitation> habUser = habilitationService.findUserById(id);
        if (habUser.isPresent()) {
            return ResponseEntity.ok(new HabilitationDto(habUser.get().getId(), habUser.get().getRoleList()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Controller pour recherche le role avec l'id
     * @param id
     * @return le role du correspondant
     */
    @GetMapping("/role/{id}")
    public Optional<Habilitation> roleHabilitation(@PathVariable String id) {
        return habilitationRepository.findById(id.toUpperCase());
    }
}
