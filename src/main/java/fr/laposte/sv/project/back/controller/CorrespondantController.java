package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/correspondant")
public class CorrespondantController {

    @Autowired
    private CorrespondantRepository correspondantRepository;

    @GetMapping("/tout")
        public Collection<Correspondant> findAll() {

        return correspondantRepository.findAll();
    }

    @GetMapping("/id/{id_RH}")
    public Optional<Correspondant> findById(@PathVariable String id_RH) {
        return correspondantRepository.findById(id_RH);
    }

    @GetMapping("nom/{nom}")
    public Optional<Correspondant> findByNom(@PathVariable String nom) {
        return correspondantRepository.findByNom(nom);
    }
    @GetMapping("fonction/{fonction}")
    public Optional<Correspondant> findByFonction(@PathVariable String fonction) {
        return correspondantRepository.findByFonction(fonction);
    }
    @GetMapping("email/{email}")
    public Optional<Correspondant> findByEmail(@PathVariable String email) {
        return correspondantRepository.findByEmail(email);
    }
    @DeleteMapping("/delete/{id}")
    public void delCorrespondant(@PathVariable String id) {
        Optional<Correspondant> optionalCorrespondant = correspondantRepository.findById(id);
      if (optionalCorrespondant.isPresent()) {
            correspondantRepository.deleteById(id);
            System.out.print("Correspondant supprimé");
//        } else {
//            System.out.print("Pas de correspondant à supprimer");
        }
        }
    }

