/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Configuration
@ComponentScan(basePackages = "az.kapitalbank.practice.accounts.db.*")
public class DbConfig {

    @Bean
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "WEBAPP", "oracle123");
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        driverManagerDataSource.setUsername("WEBAPP");
        driverManagerDataSource.setPassword("oracle123");
        return driverManagerDataSource;
    }
    
}
