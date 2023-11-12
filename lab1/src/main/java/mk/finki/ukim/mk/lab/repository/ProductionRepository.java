package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductionRepository {
    List<Production> productions = new ArrayList<>();

    public ProductionRepository() {
        Production production1 = new Production(1L, "Production1", "Country1", "Address1");
        Production production2 = new Production(2L, "Production2", "Country2", "Address2");
        Production production3 = new Production(3L, "Production3", "Country3", "Address3");
        Production production4 = new Production(4L, "Production4", "Country4", "Address4");
        Production production5 = new Production(5L, "Production5", "Country5", "Address5");

        productions.add(production1);
        productions.add(production2);
        productions.add(production3);
        productions.add(production4);
        productions.add(production5);
    }

    public List<Production> findAll(){
        return productions;
    }

    public Production findById(long id){
        return productions.stream()
                .filter(production -> production.getId()==id)
                .findFirst()
                .orElse(null);
    }
}
