package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;

public interface ProductionService {
    List<Production> findAll();
    Production findById(long id);
}
