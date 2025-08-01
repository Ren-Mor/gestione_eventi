package Ren_Mor.gestione_eventi.payloads;



import jakarta.validation.constraints.NotNull;

public record BookingRichiestaDTO(
        @NotNull(message = "ID evento obbligatorio!")
        Long eventId
) {}
