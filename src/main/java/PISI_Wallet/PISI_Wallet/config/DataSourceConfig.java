package PISI_Wallet.PISI_Wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Create a DriverManagerDataSource for SQLite connection
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Set the JDBC URL for SQLite
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:/home/brahim/PISI-WALLET/PISI_Wallet.db");  // Update this path to your SQLite file location
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}