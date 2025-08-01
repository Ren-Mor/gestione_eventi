package Ren_Mor.gestione_eventi.payloads;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record NewEventoDTO(
        @NotEmpty(message = "Titolo obbligatorio!")
        @Size(min = 2, max = 50)
        String title,
        @NotEmpty(message = "Descrizione obbligatoria!")
        String description,
        @NotNull(message = "Data obbligatoria!")
        LocalDateTime date,
        @NotEmpty(message = "Luogo obbligatorio!")
        String location,
        @NotNull(message = "Posti disponibili obbligatori!")
        Integer postiDisponibili
) {}
