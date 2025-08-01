package Ren_Mor.gestione_eventi.services;

import Ren_Mor.gestione_eventi.entities.Evento;
import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.enums.Ruolo;
import Ren_Mor.gestione_eventi.exceptions.BadRequestException;
import Ren_Mor.gestione_eventi.exceptions.NotFoundException;
import Ren_Mor.gestione_eventi.payloads.NewEventoDTO;
import Ren_Mor.gestione_eventi.payloads.NewEventoResponseDTO;
import Ren_Mor.gestione_eventi.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public NewEventoResponseDTO createEvent(User organizzatore, NewEventoDTO payload) {
        if (organizzatore.getRuolo() != Ruolo.ORGANIZZATORE)
            throw new BadRequestException("Solo gli organizzatori possono creare eventi!");

        Evento evento = new Evento(
                payload.title(),
                payload.description(),
                payload.date(),
                payload.location(),
                payload.postiDisponibili(),
                organizzatore
        );
        Evento saved = eventoRepository.save(evento);
        return new NewEventoResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getDate(),
                saved.getLocation(),
                saved.getPostiDisponibili(),
                organizzatore.getId(),
                organizzatore.getUsername()
        );
    }

    public Evento updateEvent(User organizzatore, Long id, NewEventoDTO payload) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.intValue()));
        if (!evento.getOrganizzatore().getId().equals(organizzatore.getId()))
            throw new BadRequestException("Puoi modificare solo i tuoi eventi!");
        evento.setTitle(payload.title());
        evento.setDescription(payload.description());
        evento.setDate(payload.date());
        evento.setLocation(payload.location());
        evento.setpostiDisponibili(payload.postiDisponibili());
        return eventoRepository.save(evento);
    }

    public void deleteEvent(User organizzatore, Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.intValue()));
        if (!evento.getOrganizzatore().getId().equals(organizzatore.getId()))
            throw new BadRequestException("Puoi eliminare solo i tuoi eventi!");
        eventoRepository.delete(evento);
    }
}