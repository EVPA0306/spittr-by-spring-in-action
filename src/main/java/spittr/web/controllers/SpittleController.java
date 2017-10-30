package spittr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.data.Spittle;
import spittr.exceptions.DuplicateSpittleException;
import spittr.web.repositories.SpittleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;
    private final Logger log = LoggerFactory.getLogger(SpittleController.class);

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Map model, @RequestParam(value="max", defaultValue = "1000") Long max
            , @RequestParam(value = "count", defaultValue = "20") int count) {
        model.put("spittleList",spittleRepository.findSpittles(max,count));
        //return spittleRepository.findSpittles(max,count);
        return "spittles";
    }

    @RequestMapping(value="/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable Long spittleId, Model model) {
       model.addAttribute(spittleRepository.findOne(spittleId));
        //model.put("spittle",spittleRepository.findOne(spittleId));
        return "spittle";
    }

    @RequestMapping(value = "/new/{message}", method = RequestMethod.POST)
    public String saveSpittle(@PathVariable String message, Model model) {
        log.info("REST (POST) request to save Spittle : {}", message);
        spittleRepository.save(new Spittle(124L, message, LocalDateTime.now()));
        return "redirect:/spittles";
    }
}
