package Ren_Mor.gestione_eventi.entities;

import Ren_Mor.gestione_eventi.enums.Ruolo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Ruolo ruolo;

    @Column(nullable = false)
    private String email;

    // Costruttore
    public User(String username, String password, Ruolo ruolo, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    // Setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}

