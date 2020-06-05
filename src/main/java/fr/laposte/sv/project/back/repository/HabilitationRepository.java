package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Habilitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilitationRepository extends JpaRepository<Habilitation, String> {



}
