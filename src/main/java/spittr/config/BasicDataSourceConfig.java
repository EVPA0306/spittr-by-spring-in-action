package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by evgenypavlenko on 10/27/17.
 */

public class BasicDataSourceConfig {
    //@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/spittr_db");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
