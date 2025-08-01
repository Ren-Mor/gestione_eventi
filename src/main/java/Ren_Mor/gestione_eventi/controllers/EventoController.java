// src/main/java/Ren_Mor/gestione_eventi/controllers/EventoController.java
package Ren_Mor.gestione_eventi.controllers;

import Ren_Mor.gestione_eventi.entities.Evento;
import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.payloads.NewEventoDTO;
import Ren_Mor.gestione_eventi.payloads.NewEventoResponseDTO;
import Ren_Mor.gestione_eventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEvents() {
        return eventoService.getAllEvents();
    }

    @PostMapping
    public NewEventoResponseDTO createEvent(@AuthenticationPrincipal User organizzatore,
                                            @RequestBody @Validated NewEventoDTO payload,
                                            BindingResult validationResult) {
        return eventoService.createEvent(organizzatore, payload);
    }

    @PutMapping("/{id}")
    public Evento updateEvent(@AuthenticationPrincipal User organizzatore,
                              @PathVariable Long id,
                              @RequestBody @Validated NewEventoDTO payload) {
        return eventoService.updateEvent(organizzatore, id, payload);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@AuthenticationPrincipal User organizzatore, @PathVariable Long id) {
        eventoService.deleteEvent(organizzatore, id);
    }
}