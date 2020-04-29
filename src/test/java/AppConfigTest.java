package com.mybuddy.pay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import(AppConfig.class)
public class AppConfigTest {

    private static final Logger log = LogManager.getLogger(AppConfigTest.class);

    @Bean
    public DataSource dataSource() {
        log.info("....dataSource");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("data_schema_test.sql")
                .addScript("data_init_test.sql")
                .build();
    }

}