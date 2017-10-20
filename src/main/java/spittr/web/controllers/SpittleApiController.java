package spittr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.data.Spittle;
import spittr.web.repositories.SpittleRepository;

import java.net.URI;
import java.util.List;

/**
 * Created by evgenypavlenko on 10/17/17.
 */
@RestController
@RequestMapping("/api/spittles")
public class SpittleApiController {

    private final Logger log = LoggerFactory.getLogger(SpittleApiController.class);


    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleApiController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Spittle> spittles() {
        log.info("REST (GET) request to get all Spittles : {}");
        return spittleRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById(@PathVariable Long id) {
        Spittle spittle = spittleRepository.findOne(id);
        HttpStatus status =  spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Spittle>(spittle,status);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle rbSpittle) {
        Spittle spittle = spittleRepository.save(rbSpittle);
        log.info("REST (POST) request to save a Spittle : {}", spittle);
        HttpHeaders headers = new HttpHeaders();
        URI uri = URI.create("/api/spittles/" + spittle.getId());
        headers.setLocation(uri);
        return new ResponseEntity<Spittle>(spittle,headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public Spittle updateSpittle(@RequestBody Spittle spittle) {
        log.info("REST (PUT) request to update a Spittle : {}", spittle);
        return spittleRepository.save(spittle);
    }
}
