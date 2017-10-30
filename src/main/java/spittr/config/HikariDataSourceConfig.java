package spittr.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by evgenypavlenko on 10/20/17.
 */
@Component
public class HikariDataSourceConfig {
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.impossibl.postgres.jdbc.PGDriver");
        hikariDataSource.setJdbcUrl("jdbc:pgsql://localhost:5432/spittr_db");
        hikariDataSource.setUsername("spittr");
        hikariDataSource.setMaximumPoolSize(10);
        return hikariDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
