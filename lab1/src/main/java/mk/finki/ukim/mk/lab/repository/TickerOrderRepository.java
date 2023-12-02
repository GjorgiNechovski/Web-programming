package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TickerOrderRepository extends JpaRepository<TicketOrder, Long> {
    void deleteById(long id);
}
