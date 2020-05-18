package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.exception.ExistingUserNameException;
import fr.laposte.sv.project.back.exception.InvalidCredentialsException;
import fr.laposte.sv.project.back.model.Habilitation;
import fr.laposte.sv.project.back.repository.HabilitationRepository;
import fr.laposte.sv.project.back.security.JwtTokenProvider;
import fr.laposte.sv.project.back.service.HabilitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilitationServiceImpl implements HabilitationService, UserDetailsService {

    @Autowired
    private HabilitationRepository habilitationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String signin(String id, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, password));
            return jwtTokenProvider.createToken(id, habilitationRepository.findById(id).get().getRoleList());
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public Habilitation signup(Habilitation user) throws ExistingUserNameException {
        if (!habilitationRepository.existsById(user.getId())) {
            Habilitation userToSave = new Habilitation(user.getId(), passwordEncoder.encode(user.getMotDePasse()), user.getRoleList());
            return habilitationRepository.save(userToSave);
//            return jwtTokenProvider.createToken(user.getId(), user.getRoleList());
        } else {
            throw new ExistingUserNameException();
        }
    }

    @Override
    public List<Habilitation> findAllUsers() {
        return habilitationRepository.findAll();
    }

    @Override
    public Optional<Habilitation> findUserById(String username) {
        return habilitationRepository.findById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final Optional<Habilitation> user = habilitationRepository.findById(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Habilitation '" + username + "' introuvable");
        }
        return User
                .withUsername(username)
                .password(user.get().getMotDePasse())
                .authorities(user.get().getRoleList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
