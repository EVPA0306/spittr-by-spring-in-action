package spittr.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by evgenypavlenko on 10/20/17.
 */
@Component
public class HikariDataSourceConfig {
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("org.h2.Driver");
        hikariDataSource.setJdbcUrl("jdbc:h2:tcp://localhost/~/spitter");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setMaximumPoolSize(10);
        return hikariDataSource;
    }
}
