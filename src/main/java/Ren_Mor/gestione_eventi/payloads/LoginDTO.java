package Ren_Mor.gestione_eventi.payloads;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "Username  obbligatorio!")
        String email,
        @NotEmpty(message = "Password obbligatoria!")
        String password
) {}
