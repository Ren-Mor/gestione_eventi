 package Ren_Mor.gestione_eventi.services;

import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.exceptions.BadRequestException;
import Ren_Mor.gestione_eventi.payloads.NewUserDTO;
import Ren_Mor.gestione_eventi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    public Page<User> findAll(int page, int size, String sortBy) {
        return usersRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public User findById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Utente non trovato"));
    }

    public User findByIdAndUpdate(Long userId, NewUserDTO payload) {
        User found = this.findById(userId);

        if (!found.getEmail().equals(payload.email())) {
            usersRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });
        }

        found.setEmail(payload.email());
        found.setPassword(payload.password());
        // found.setAvatarURL("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname()); // decommenta se serve

        User modifiedUser = usersRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modifiedUser;
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Utente non trovato con email: " + email));
    }

    public User update(Long userId, NewUserDTO payload) {
        return findByIdAndUpdate(userId, payload);
    }

    public void delete(Long userId) {
        User user = findById(userId);
        usersRepository.delete(user);
    }
}