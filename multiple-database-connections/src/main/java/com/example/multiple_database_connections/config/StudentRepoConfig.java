package com.example.multiple_database_connections.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.multiple_database_connections.studentRepository",
        entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager"
)
public class StudentRepoConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.student")
    public DataSourceProperties studentDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name="studentDataSource")
    @Primary
    public DataSource studentDataSource() {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(studentDataSourceProperties().getDriverClassName());
        dataSource.setUrl(studentDataSourceProperties().getUrl());
        dataSource.setUsername(studentDataSourceProperties().getUsername());
        dataSource.setPassword(studentDataSourceProperties().getPassword());
        return dataSource;
    }

    @Bean(name="studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(EntityManagerFactoryBuilder emfb,
                                                                       @Qualifier("studentDataSource") DataSource dataSource) {
        return emfb
                .dataSource(dataSource)
                .packages("com.example.multiple_database_connections.student")
//                .persistenceUnit("student")
                .build();
    }

    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}
