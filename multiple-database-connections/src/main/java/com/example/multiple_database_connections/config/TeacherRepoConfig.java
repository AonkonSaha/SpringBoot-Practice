package com.example.multiple_database_connections.config;

import com.zaxxer.hikari.HikariDataSource;
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
@EnableJpaRepositories(
        basePackages = "com.example.multiple_database_connections.techerRepository",
        entityManagerFactoryRef = "teacherEntityManagerFactory",
        transactionManagerRef = "teacherTransactionManager"
)
@EnableTransactionManagement
public class TeacherRepoConfig {

    @Bean
    @ConfigurationProperties( prefix = "spring.datasource.teacher")
    public DataSourceProperties teacherDataSourceProperties(){
        return new DataSourceProperties();
    }

//    @Bean // Bean Name:- (Function Name: teacherDataSource)
//    public DataSource teacherDataSource(){
//        DriverManagerDataSource dataSource=new DriverManagerDataSource();
//        dataSource.setDriverClassName(teacherDataSourceProperties().getDriverClassName());
//        dataSource.setUrl(teacherDataSourceProperties().getUrl());
//        dataSource.setUsername(teacherDataSourceProperties().getUsername());
//        dataSource.setPassword(teacherDataSourceProperties().getPassword());
//        return dataSource;
//    }

    @Bean // Bean Name:- (Function Name: teacherDataSource)
    public DataSource teacherDataSource(){
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setDriverClassName(teacherDataSourceProperties().getDriverClassName());
        dataSource.setJdbcUrl(teacherDataSourceProperties().getUrl());
        dataSource.setUsername(teacherDataSourceProperties().getUsername());
        dataSource.setPassword(teacherDataSourceProperties().getPassword());
        return dataSource;
    }

    @Bean // Bean Name:- (Function Name: teacherEntityManagerFactory)
    public LocalContainerEntityManagerFactoryBean teacherEntityManagerFactory(
            EntityManagerFactoryBuilder emfb ,
            @Qualifier("teacherDataSource") DataSource dataSource){

        return emfb
                .dataSource(dataSource)
                .packages("com.example.multiple_database_connections.teacher")
                .build();
    }

    @Bean
    public PlatformTransactionManager teacherTransactionManager(@Qualifier("teacherEntityManagerFactory") EntityManagerFactory emf){

        return new JpaTransactionManager(emf);

    }



}
