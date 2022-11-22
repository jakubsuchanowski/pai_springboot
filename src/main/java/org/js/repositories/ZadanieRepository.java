package org.js.repositories;

import org.js.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {
    void deleteById(Long id);
    List<Zadanie> findByWykonane(Boolean wykonane);
    List<Zadanie> findByKosztLessThan(Double koszt);
    List<Zadanie> findByKosztBetween(Double kosztMin, Double kosztMax);

}
