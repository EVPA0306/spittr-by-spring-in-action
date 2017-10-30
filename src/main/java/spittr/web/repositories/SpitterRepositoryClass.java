package spittr.web.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spittr.data.Spitter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static spittr.web.repositories.SpitterRepository.*;

/**
 * Created by evgenypavlenko on 10/16/17.
 */
@Component
public class SpitterRepositoryImpl /*implements SpitterRepository*/ {

    private final Logger log = LoggerFactory.getLogger(SpitterRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    private static final Spitter spitter_static;


    static {
        spitter_static =
        new Spitter(
                24L
                ,"JBauer"
                ,"24hours"
                ,"Jack"
                , "Bauer"
        );
    }

    //private Connection conn;
    //private PreparedStatement stmt;
    //private ResultSet rs;

//curl -d "@spitter.json" -X POST -H "Content-Type: application/json" localhost:8880/spittr/api/spitters/
//curl -d '{"id":"5", "userName":"SBevz", "firstName":"Sasha", "lastName":"Bevz", "password":"None"}' -X POST -H "Content-Type: application/json" localhost:8880/spittr/api/spitters/
    //@Override
    public Spitter save(Spitter unsaved) {
        Spitter saved = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            log.info("Connection for : " + dataSource +" connection: " + conn);
            stmt = conn.prepareStatement(SQL_INSERT_SPITTER);
            log.info("Statement#1 " + stmt);
            stmt.setLong(1, unsaved.getId());
            stmt.setString(2, unsaved.getUserName());
            stmt.setString(3, unsaved.getFirstName());
            stmt.setString(4, unsaved.getLastName());
            stmt.setString(5, unsaved.getPassword());
            log.info("Statement#2 " + unsaved.getId() +" " + unsaved.getUserName() + " " + unsaved.getFirstName()
                    + " " + unsaved.getLastName() + " " + unsaved.getPassword());
            if (stmt.executeUpdate() > 0) {
                log.info("Executed for: " + unsaved);
                //conn.commit();
                saved = findById(Long.valueOf(unsaved.getId()));
                log.info("Saved: " + saved);
            }
        } catch (SQLException se) {
            log.error("SQL error in save(): " + se.getMessage());
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    log.error("SQL error in save(): " + se.getMessage());
                }
            }
        }
        return saved;
    }

    //@Override
    public Spitter findByUsername(String username) {
        return spitter_static;
    }

    //@Override
    public List<Spitter> findAll() {
        List<Spitter> res = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        log.info("Looking for All Spitters");
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_ALL_SPITTERS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Spitter spitter = new Spitter(
                        rs.getLong("id")
                        , rs.getString("username")
                        , rs.getString("firstname")
                        , rs.getString("lastname")
                        , rs.getString("password")
                );
                res.add(spitter);
            }
        } catch (SQLException se) {
            log.error("SQL error in findAll(): " + se.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    log.error("SQL error in findAll(): " + se.getMessage());
                }
            }
        }
        return res;
    }

    //@Override
    public Spitter findById(Long id) {

        Spitter spitter = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        log.info("Looking for Spitter with an ID: " + id);

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_SPITTER);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                spitter = new Spitter();
                spitter.setId(rs.getLong("id"));
                spitter.setUserName(rs.getString("username"));
                spitter.setFirstName(rs.getString("firstname"));
                spitter.setLastName(rs.getString("lastname"));
                spitter.setPassword(rs.getString("password"));
            }
        } catch (SQLException se) {
            log.error("SQL error: " + se.getMessage());
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException se) {
                    log.error("SQL error while closing stmt: " + se.getMessage());
                }
        }
        return spitter;
    }

}
