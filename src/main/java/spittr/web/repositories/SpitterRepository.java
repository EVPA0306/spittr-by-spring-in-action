package spittr.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spittr.data.Spitter;

import java.util.List;
import java.util.Optional;

/**
 * Created by evgenypavlenko on 10/16/17.
 */

@Repository
public interface SpitterRepository {

    public static final String SQL_SELECT_SPITTER =
            "select id, username, firstname, lastname, password from spitter where id = ?";
    public static final String SQL_ALL_SPITTERS =
            "select id, username, firstname, lastname, password from spitter";
    public static final String SQL_INSERT_SPITTER =
            "INSERT INTO spitter (id, username, firstname, lastname, password) VALUES (?,?,?,?,?);";

    Spitter save(Spitter unsaved);
    Spitter findByUsername(String username);
    List<Spitter> findAll();
    Optional<Spitter> findById(Long id);
}
