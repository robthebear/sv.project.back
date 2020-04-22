package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.exception.ExistingUserNameException;
import fr.laposte.sv.project.back.exception.InvalidCredentialsException;
import fr.laposte.sv.project.back.model.Habilitation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HabilitationService {

    String signin(String username, String password) throws InvalidCredentialsException;

    Habilitation signup(Habilitation user) throws ExistingUserNameException;

    List<Habilitation> findAllUsers();

//    Optional<Habilitation> findUserByUserName(String username);

    Optional<Habilitation> findUserById(String id);
}
