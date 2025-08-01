// src/main/java/Ren_Mor/gestione_eventi/services/BookingService.java
package Ren_Mor.gestione_eventi.services;

import Ren_Mor.gestione_eventi.entities.Booking;
import Ren_Mor.gestione_eventi.entities.Evento;
import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.exceptions.BadRequestException;
import Ren_Mor.gestione_eventi.exceptions.NotFoundException;
import Ren_Mor.gestione_eventi.payloads.BookingResponseDTO;
import Ren_Mor.gestione_eventi.payloads.BookingRichiestaDTO;
import Ren_Mor.gestione_eventi.repositories.BookingRepository;
import Ren_Mor.gestione_eventi.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EventoRepository eventoRepository;

    public BookingResponseDTO bookEvent(User user, BookingRichiestaDTO richiesta) {
        Evento evento = eventoRepository.findById(richiesta.eventId())
                .orElseThrow(() -> new NotFoundException(richiesta.eventId().intValue()));

        long prenotati = bookingRepository.findByEventId(evento.getId()).size();
        if (prenotati >= evento.getPostiDisponibili())
            throw new BadRequestException("Posti esauriti per questo evento!");

        boolean alreadyBooked = bookingRepository.findByUserId(user.getId()).stream()
                .anyMatch(b -> b.getEvent().getId().equals(evento.getId()));
        if (alreadyBooked)
            throw new BadRequestException("Hai già prenotato questo evento!");

        Booking booking = new Booking(evento, user);
        Booking saved = bookingRepository.save(booking);

        return new BookingResponseDTO(
                saved.getId(),
                evento.getId(),
                evento.getTitle(),
                user.getId(),
                user.getUsername()
        );
    }

    public List<BookingResponseDTO> getMyBookings(User user) {
        List<Booking> bookings = bookingRepository.findByUserId(user.getId());
        return bookings.stream().map(b -> new BookingResponseDTO(
                b.getId(),
                b.getEvent().getId(),
                b.getEvent().getTitle(),
                user.getId(),
                user.getUsername()
        )).toList();
    }
}
