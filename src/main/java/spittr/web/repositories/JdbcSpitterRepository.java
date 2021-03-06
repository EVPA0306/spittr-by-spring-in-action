package spittr.web.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import spittr.data.Spitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by evgenypavlenko on 10/29/17.
 */
@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    //@Override
    public Spitter save(Spitter unsaved) {
        jdbcOperations.update(SpitterRepository.SQL_INSERT_SPITTER
                , unsaved.getId()
                , unsaved.getUserName()
                , unsaved.getFirstName()
                , unsaved.getLastName()
                , unsaved.getPassword()
        );
        return findById(unsaved.getId()).get();
    }

    //@Override
    public Spitter findByUsername(String username) {
        throw new UnsupportedOperationException("This method has not implemented yet");
    }

    //@Override
    public List<Spitter> findAll() {

        List<Spitter> spitterList = new ArrayList<>();
        List<Map<String,Object>> rows = jdbcOperations.queryForList(SpitterRepository.SQL_ALL_SPITTERS);

        for (Map<String,Object> row : rows) {
            Spitter spitter = new Spitter();
            spitter.setId(Long.valueOf(row.get("id").toString()));
            spitter.setUserName(row.get("username").toString());
            spitter.setFirstName(row.get("firstname").toString());
            spitter.setLastName(row.get("lastname").toString());
            spitter.setPassword(row.get("password").toString());
            spitterList.add(spitter);
        }
        return spitterList;
    }

    //@Override
    public Optional<Spitter> findById(Long id) {
        return jdbcOperations.queryForObject(SpitterRepository.SQL_SELECT_SPITTER
                , (rs, rowNum) -> {
                    return Optional.of(new Spitter(
                            rs.getLong("id")
                            , rs.getString("username")
                            , rs.getString("firstname")
                            , rs.getString("lastname")
                            , rs.getString("password")
                    ));
                }
                , id
        );
    }

}
