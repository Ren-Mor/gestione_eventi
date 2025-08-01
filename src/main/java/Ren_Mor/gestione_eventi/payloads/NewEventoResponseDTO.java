package Ren_Mor.gestione_eventi.payloads;

import java.time.LocalDateTime;

public record NewEventoResponseDTO(
        Long id,
        String title,
        String description,
        LocalDateTime date,
        String location,
        int postiDisponibili,
        Long organizzatoreId,
        String organizzatoreUsername
) {}
