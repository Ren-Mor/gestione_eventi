package Ren_Mor.gestione_eventi.repositories;


import Ren_Mor.gestione_eventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
