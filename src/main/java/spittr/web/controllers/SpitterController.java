package spittr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.data.Spitter;
import spittr.web.repositories.SpitterRepository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private final Logger log = LoggerFactory.getLogger(SpitterController.class);

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    public SpitterController() {

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Map model) {
        log.info("REST (GET) request to get username : {}", username);
        Spitter spitter = spitterRepository.findByUsername(username);
        log.info("REST (GET) request to get spitter : {}", spitter);
        model.put("spitter", spitter);
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(Spitter spitter, @RequestPart("profilePicture") MultipartFile profilePicture,
                                      RedirectAttributes model)
            throws IOException {
        log.info("REST (POST-register) request to save spitter : {}", spitter);
        log.info("REST (POST-register) request to save profilePicture : {}", profilePicture);
        spitterRepository.save(spitter);
        //Save profile pict
        //saveImage(profilePicture);
        profilePicture.transferTo(new File("/tmp/data/spittr/" + profilePicture.getOriginalFilename()));
        model.addAttribute("username",spitter.getUserName());
        model.addFlashAttribute("spitter",spitter);
        return "redirect:/spitter/{username}";
    }

    private void saveImage(MultipartFile image) throws HttpRequestMethodNotSupportedException {
        //throw new UnsupportedOperationException("This method has not implemented yet");
        throw new HttpRequestMethodNotSupportedException("This method has not implemented yet");
    }
}
