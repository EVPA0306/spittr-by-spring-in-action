package spittr.web.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import spittr.data.Spittle;
import spittr.exceptions.DuplicateSpittleException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evgenypavlenko on 10/16/17.
 */

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

    @Override
    public List<Spittle> findSpittles(Long max, int count) {
        return createSpittleList(count);
    }

    @Override
    public Spittle findOne(Long id) {
        return new Spittle(id,"Spittle " + id, LocalDateTime.now());
    }

    private List<Spittle> createSpittleList(int count){
        List<Spittle> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Spittle(Long.valueOf(i), "Spittle " + i, LocalDateTime.now()));
        }
        return list;
    }

    @Override
    public Spittle save(Spittle spittle) {
        return new Spittle(1973L, "Evgeny", LocalDateTime.now());
        //throw new DuplicateSpittleException("The Spittle already exists");
    }

    @Override
    public List<Spittle> findAll() {
        List<Spittle> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Spittle(Long.valueOf(i), "Spittle REST" + i, LocalDateTime.now()));
        }
        return list;
    }
}

