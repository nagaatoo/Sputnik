//package com.numbDev.Sputnik.Configuration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "SputnikDbConfBean",
//        transactionManagerRef = "secondTransactionManager",
//        basePackages = "com.marcosbarbero.wd.pcf.multidatasources.second.repository"
//)
//@EnableTransactionManagement
//public class SputnikDbConf {
//
//    @Primary
//    @Bean(name = "SputnikDbConfBean")
//    public LocalContainerEntityManagerFactoryBean SputnikDbConfBean(final EntityManagerFactoryBuilder builder,
//                                                                    final @Qualifier("sputnik-db")DataSource dataSource) {
//
//        return builder.dataSource(dataSource).packages().persistenceUnit("sputnik-db").properties()
//    }
//}
