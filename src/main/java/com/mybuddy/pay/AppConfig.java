package com.mybuddy.pay;

import com.mybuddy.pay.dao.RelationDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// TODO a voir si @ComponentScan( est indispensable

@Configuration
@ComponentScan("com.mybuddy")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {

    private static final Logger log = LogManager.getLogger(AppConfig.class);

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
        log.info("oracle.jdbc.driver.OracleDriver.class.getName():"+oracle.jdbc.driver.OracleDriver.class.getName());
        log.info("env.username:"+env.getProperty("jdbc.username"));
        log.info("env.password:"+env.getProperty("jdbc.password"));
        log.info("env.url:"+env.getProperty("jdbc.url"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate () {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    PlatformTransactionManager platformTransactionManager () {
        return new DataSourceTransactionManager(dataSource());
    }

}