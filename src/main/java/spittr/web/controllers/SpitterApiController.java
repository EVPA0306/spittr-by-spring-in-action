package spittr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spittr.data.Spitter;
import spittr.data.Spittle;
import spittr.web.repositories.SpitterRepository;

import java.net.URI;
import java.util.List;

/**
 * Created by evgenypavlenko on 10/27/17.
 */
@RestController
@RequestMapping("/api/spitters")
public class SpitterApiController {

    private final Logger log = LoggerFactory.getLogger(SpitterApiController.class);

    @Autowired
    SpitterRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Spitter> spitterById(@PathVariable Long id) {
        Spitter spitter = repository.findById(id);
        log.info("Executing REST-GET for {} ", spitter);
        HttpStatus status =  spitter != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Spitter>(spitter,status);
    }

    @GetMapping()
    public List<Spitter> spitters() {
        return repository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Spitter> createSpitter(@RequestBody Spitter requestSpitter) {
        log.info("REST (POST) request to save a requestSpitter : {}", requestSpitter);
        Spitter spitter = repository.save(requestSpitter);
        log.info("REST (POST) request to save a spitter : {}", spitter);
        HttpHeaders headers = new HttpHeaders();
        URI uri = URI.create("/api/spitters/" + spitter.getId());
        headers.setLocation(uri);
        return new ResponseEntity<Spitter>(spitter,headers,HttpStatus.CREATED);
    }
}
