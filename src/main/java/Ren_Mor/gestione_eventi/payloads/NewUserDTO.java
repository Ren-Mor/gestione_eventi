package Ren_Mor.gestione_eventi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 2, max = 25, message = "Il nome deve essere di lunghezza compresa tra 2 e 25")
        String username,
        @NotEmpty(message = "L'email è obbligatoria!")
        String email,
        @NotEmpty(message = "La password è obbligatoria!")
        @Size(min = 4)
        String password) {
}
