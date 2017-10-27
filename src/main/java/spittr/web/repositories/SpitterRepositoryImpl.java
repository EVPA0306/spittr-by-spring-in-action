package spittr.web.repositories;

import org.springframework.stereotype.Component;
import spittr.data.Spitter;

/**
 * Created by evgenypavlenko on 10/16/17.
 */
@Component
public class SpitterRepositoryImpl implements SpitterRepository {

    private final static Spitter spitter;

    static {
        spitter =
        new Spitter(
                24L
                ,"JBauer"
                ,"24hours"
                ,"Jack"
                , "Bauer"
        );
    }

    @Override
    public Spitter save(Spitter unsaved) {
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        return spitter;
    }

}
