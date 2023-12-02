package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
    Production findById(long id);
}
