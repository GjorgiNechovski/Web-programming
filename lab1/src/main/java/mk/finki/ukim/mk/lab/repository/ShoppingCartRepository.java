package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
    List<ShoppingCart> findByDateCreatedAfter(LocalDateTime from);
    List<ShoppingCart> findByDateCreatedBefore(LocalDateTime to);
}
