package Ren_Mor.gestione_eventi.services;

import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.enums.Ruolo;
import Ren_Mor.gestione_eventi.exceptions.BadRequestException;
import Ren_Mor.gestione_eventi.exceptions.NotFoundException;
import Ren_Mor.gestione_eventi.payloads.NewUserDTO;
import Ren_Mor.gestione_eventi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(NewUserDTO payload, Ruolo ruolo) {
        // Verifica email unica
        Optional<User> existing = userRepository.findByEmail(payload.email());
        if (existing.isPresent()) {
            throw new BadRequestException("L'email " + payload.email() + " è già in uso!");
        }

        // Crea utente
        User newUser = new User(
                payload.username(),
                passwordEncoder.encode(payload.password()),
                ruolo,
                payload.email()
        );

        User savedUser = userRepository.save(newUser);
        log.info("Utente con id: " + savedUser.getId() + " registrato correttamente.");
        return savedUser;
    }

    public Page<User> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return userRepository.findAll(pageable);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId.intValue()));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("L'utente con l'email " + email + " non è stato trovato!"));
    }
}