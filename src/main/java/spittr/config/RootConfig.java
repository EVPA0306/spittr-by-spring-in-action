package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spittr.data.Spittle;
import spittr.web.repositories.SpittleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"spittr"})
public class RootConfig {

}
