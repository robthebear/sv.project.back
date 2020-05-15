package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface SvSuiviRepository extends JpaRepository<SvSuivi, Integer> {


    Set<SvSuivi> findSvSuiviByWebService(WebService webService);

    Set<SvSuivi> findByDate(LocalDate date);


//    Set<SvSuivi> svSuiviParDate();


//    SvSuivi findAll(LocalDate date);
}
