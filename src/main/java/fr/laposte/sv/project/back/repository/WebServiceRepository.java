package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebServiceRepository extends JpaRepository<WebService, Integer> {
}
