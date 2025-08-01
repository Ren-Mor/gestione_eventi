package Ren_Mor.gestione_eventi.payloads;


public record BookingResponseDTO(
        Long id,
        Long eventId,
        String eventTitle,
        Long userId,
        String username
) {}
