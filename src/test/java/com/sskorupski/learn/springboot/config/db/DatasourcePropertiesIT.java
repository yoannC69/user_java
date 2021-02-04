package com.sskorupski.learn.springboot.config.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DatasourcePropertiesIT {

    @Autowired
    private DatasourceProperties datasourceProperties;

    @Test
    public void contextLoads() throws Exception {
        assertThat(datasourceProperties).isNotNull();
        assertThat(datasourceProperties.getDriverClassName()).isNotNull();
        assertThat(datasourceProperties.getPassword()).isNotNull();
        assertThat(datasourceProperties.getUsername()).isNotNull();
        assertThat(datasourceProperties.getUrl()).isNotNull();
    }
}
