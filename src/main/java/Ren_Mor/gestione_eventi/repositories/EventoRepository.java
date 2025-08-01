package Ren_Mor.gestione_eventi.repositories;

import Ren_Mor.gestione_eventi.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByOrganizzatore_Id(Long organizzatoreId);
}
