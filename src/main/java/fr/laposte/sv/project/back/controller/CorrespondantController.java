package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.model.Role;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import fr.laposte.sv.project.back.service.CorrespondantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/correspondant")
public class CorrespondantController {

    @Autowired
    private CorrespondantRepository correspondantRepository;

    @Autowired
    private CorrespondantService correspondantService;

    /**
     * Methode qui permet de rechercher tous les correspondants
     * @return la liste de tous les correspondants
     */
    @GetMapping
    public Collection<Correspondant> findAll() {

        return correspondantRepository.findAll();
    }

    /**
     * Recherche un correspondant par id
     * @param id
     * @return le correspondant qui depend de cet id
     */
    @GetMapping("/id/{id}")
    public Optional<Correspondant> findById(@PathVariable String id) {
        return correspondantRepository.findById(id.toUpperCase());
    }
//TODO Créer une méthode qui permet, avec un string de rechercher dans toute la liste des correspondants, nom, prenom ou idRH

    @GetMapping("/{nom}")
    public Optional<Correspondant> findByNom(@PathVariable String nom) {
        return correspondantRepository.findByNom(nom);
    }

    @GetMapping("/{fonction}")
    public Optional<Correspondant> findByFonction(@PathVariable String fonction) {
        return correspondantRepository.findByFonction(fonction);
    }

    @GetMapping("/{email}")
    public Optional<Correspondant> findByEmail(@PathVariable String email) {
        return correspondantRepository.findByEmail(email);
    }

    /**
     * Permet de supprimer un correspondant par son id
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delCorrespondant(@PathVariable String id) {
        Optional<Correspondant> optionalCorrespondant = correspondantRepository.findById(id.toUpperCase());
        if (optionalCorrespondant.isPresent()) {
            correspondantRepository.deleteById(id.toUpperCase());
            System.out.print("Correspondant supprimé");
//        } else {
//            System.out.print("Pas de correspondant à supprimer");
        }
    }
//        @PutMapping("/update")
//    public ResponseEntity<Correspondant> updateCorrespondant(@RequestBody(required = false) Correspondant correspondant) {
//        System.out.println(correspondant);
//        return correspondantService.updateCorrespondant(correspondant);
//        }

    /**
     * Permet de mettre à jour un correspondant par son id
     * @param id
     * @param correspondant
     * @return sauvegarde les modifications du correspondant
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Correspondant> updateCorrespondant(@PathVariable String id, @Valid @RequestBody Correspondant correspondant) {

        return correspondantService.updateCorrespondant(id.toUpperCase(), correspondant);
    }
}

