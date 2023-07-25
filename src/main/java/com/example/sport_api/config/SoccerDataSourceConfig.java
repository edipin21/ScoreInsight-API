package com.example.sport_api.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.sport_api.repositories.soccer", entityManagerFactoryRef = "soccerDBEntityManagerFactory", transactionManagerRef = "soccerDBTransactionManager")
public class SoccerDataSourceConfig {

    Dotenv dotenv = Dotenv.load();

    @Primary
    @Bean("soccerDBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean soccerDBEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(soccerDataSource());
        em.setPackagesToScan("com.example.sport_api.models.sport");
        em.setPersistenceUnitName("soccer");
        em.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());
        em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");

        return em;
    }

    @Primary
    @Bean
    public DataSource soccerDataSource() {
        return DataSourceBuilder
                .create()
                .url(dotenv.get("SOCCER_DB_URL"))
                .username(dotenv.get("SOCCER_DB_USER_NAME"))
                .password(dotenv.get("SOCCER_DB_PASSWORD"))
                .driverClassName(dotenv.get("SOCCER_DB_DRIVER_CLASS_NAME"))
                .build();
    }

    @Primary
    @Bean("soccerDBTransactionManager")
    public PlatformTransactionManager soccerDBTransactionManager(EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }

}
