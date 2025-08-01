package Ren_Mor.gestione_eventi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id) {
        super("La risorsa con id " + id + " non è stata trovata!");
    }
}
