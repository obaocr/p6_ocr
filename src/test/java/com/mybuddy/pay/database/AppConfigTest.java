package com.mybuddy.pay.database;

import com.mybuddy.pay.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan
class AppConfigTest {

    @Test
    void dataSource() {
        AppConfig appConfig = new AppConfig();
        DataSource dataSource = appConfig.dataSource();
        assertNotNull(dataSource);
    }

}