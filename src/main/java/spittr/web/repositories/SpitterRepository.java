package spittr.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import spittr.data.Spitter;

/**
 * Created by evgenypavlenko on 10/16/17.
 */

@Component
public interface SpitterRepository /*extends JpaRepository<Spitter,Long>*/ {
    Spitter save(Spitter unsaved);
    Spitter findByUsername(String username);
}
