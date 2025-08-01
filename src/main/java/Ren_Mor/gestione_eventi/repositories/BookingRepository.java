package Ren_Mor.gestione_eventi.repositories;

import Ren_Mor.gestione_eventi.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);
    List<Booking> findByEvent_Id(Long eventId);
}
