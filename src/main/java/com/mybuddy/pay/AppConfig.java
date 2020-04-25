package com.mybuddy.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.mybuddy")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        //ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
        ds.setDriverClassName(env.getProperty("jdbc.driver"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));
        System.out.println("oracle.jdbc.driver.OracleDriver.class.getName():"+oracle.jdbc.driver.OracleDriver.class.getName());
        System.out.println("env.username:"+env.getProperty("jdbc.username"));
        System.out.println("env.password:"+env.getProperty("jdbc.password"));
        System.out.println("env.url:"+env.getProperty("jdbc.url"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate () {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}