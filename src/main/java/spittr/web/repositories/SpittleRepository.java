package spittr.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spittr.data.Spittle;

import java.util.List;

/**
 * Created by evgenypavlenko on 10/7/17.
 */

@Repository(value = "spittleRepository")
public interface SpittleRepository {
    List<Spittle> findSpittles(Long max, int count);
    Spittle findOne(Long id);
    Spittle save(Spittle spittle);
    List<Spittle> findAll();
}