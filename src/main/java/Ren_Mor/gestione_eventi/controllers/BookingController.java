package Ren_Mor.gestione_eventi.controllers;

import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.payloads.BookingResponseDTO;
import Ren_Mor.gestione_eventi.payloads.BookingRichiestaDTO;
import Ren_Mor.gestione_eventi.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingResponseDTO bookEvent(@AuthenticationPrincipal User user,
                                        @RequestBody @Validated BookingRichiestaDTO richiesta) {
        return bookingService.bookEvent(user, richiesta);
    }

    @GetMapping("/me")
    public List<BookingResponseDTO> getMyBookings(@AuthenticationPrincipal User user) {
        return bookingService.getMyBookings(user);
    }
}