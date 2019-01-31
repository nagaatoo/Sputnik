package com.numbDev.Sputnik.Configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;


public class CloudConf {
    @Configuration
    @Profile("cloud")
    static class CloudConfiguration extends AbstractCloudConfig{

        @Bean
        public DataSource dataSource() {
            //new CloudFactory().getCloud().getServiceInfos().get(0).getId()
        List<String> dataSourceNames = Arrays.asList("TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator", "BasicDbcpPooledDataSourceCreator");
        DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
        return connectionFactory().dataSource("sputnik-db" , dbConfig);
        }
    }

    @Configuration
    @Profile("default")
    static class LocalConfiguration {

        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:postgresql://localhost/db");
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");
            return dataSource;
        }
    }

    @Configuration
    @Profile("lazyMan")
    @ServiceScan
    static class LazyConf {

    }
}

