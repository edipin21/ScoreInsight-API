package com.example.sport_api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(basePackages = "com.example.sport_api.repositories.users", entityManagerFactoryRef = "usersDBEntityManagerFactory", transactionManagerRef = "usersDBTransactionManager")
public class UsersDataSourceConfig {

    Dotenv dotenv = Dotenv.load();

    @Bean("usersDBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usersDBEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(usersDataSource());
        em.setPackagesToScan("com.example.sport_api.models.users");
        em.setPersistenceUnitName("users");
        em.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());
        em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        em.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");

        return em;
    }

    @Primary
    @Bean
    public DataSource usersDataSource() {
        return DataSourceBuilder
                .create()
                .url(dotenv.get("USERS_DB_URL"))
                .username(dotenv.get("USERS_DB_USER_NAME"))
                .password(dotenv.get("USERS_DB_PASSWORD"))
                .driverClassName(dotenv.get("USERS_DB_DRIVER_CLASS_NAME"))
                .build();
    }

    @Bean("usersDBTransactionManager")
    public PlatformTransactionManager usersDBTransactionManager(
            @Qualifier("usersDBEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }
}
