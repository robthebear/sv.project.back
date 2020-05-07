package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import fr.laposte.sv.project.back.service.CorrespondantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/correspondant")
public class CorrespondantController {

    @Autowired
    private CorrespondantRepository correspondantRepository;

    @Autowired
    private CorrespondantService correspondantService;

    @GetMapping
        public Collection<Correspondant> findAll() {

        return correspondantRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Correspondant> findById(@PathVariable String id) {
        return correspondantRepository.findById(id);
    }

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
    @DeleteMapping("/{id}")
    public void delCorrespondant(@PathVariable String id) {
        Optional<Correspondant> optionalCorrespondant = correspondantRepository.findById(id);
      if (optionalCorrespondant.isPresent()) {
            correspondantRepository.deleteById(id);
            System.out.print("Correspondant supprimé");
//        } else {
//            System.out.print("Pas de correspondant à supprimer");
        }
        }
        @PutMapping("/update")
    public ResponseEntity<Correspondant> updateCorrespondant(@RequestBody(required = false) Correspondant correspondant) {
        System.out.println(correspondant);
        return correspondantService.updateCorrespondant(correspondant);
        }
    }

