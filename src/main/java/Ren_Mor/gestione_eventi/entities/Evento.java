package Ren_Mor.gestione_eventi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "events")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private User organizzatore;

    // Costruttore
    public Evento(String title, String description, LocalDateTime date, String location, int postiDisponibili, User organizzatore) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.postiDisponibili = postiDisponibili;
        this.organizzatore = organizzatore;
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setpostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public void setOrganizer(User organizzatore) {
        this.organizzatore = organizzatore;
    }
}
