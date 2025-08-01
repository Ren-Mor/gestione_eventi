package Ren_Mor.gestione_eventi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Costruttore
    public Booking(Evento event, User user) {
        this.event = event;
        this.user = user;
    }

    // Setter
    public void setEvent(Evento event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
