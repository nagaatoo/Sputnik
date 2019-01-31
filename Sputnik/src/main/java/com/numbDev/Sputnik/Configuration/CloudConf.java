package com.numbDev.Sputnik.Configuration;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
//@ServiceScan
public class CloudConf extends AbstractCloudConfig {

    @Bean
   // @ConfigurationProperties(prefix = DataSourceProperties)
    public DataSource dataSource() {
        //new CloudFactory().getCloud().getServiceInfos().get(0).getId()
        List<String> dataSourceNames = Arrays.asList("TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator", "BasicDbcpPooledDataSourceCreator");
        DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
        return connectionFactory().dataSource("sputnik-db" , dbConfig);
    }
}
//public class CloudConf {
//
//}
//
