package com.nicholas.receipts.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;


@Configuration
@EntityScan("com.nicholas.receipts.models")
@EnableJpaRepositories(basePackages = "com.nicholas.receipts.repository")
public class JpaConfiguration {
    private final DataSource dataSource;

    public JpaConfiguration(DataSource dataSource){this.dataSource = dataSource;}
    @Bean(name="transactionManager")
    public JpaTransactionManager jpaTransactionManager(){
        final JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(dataSource);
        Properties props = new Properties();
        props.setProperty("jakarta.persistence.schema-generation.database.action", "create");
        tm.setJpaProperties(props);
        return tm;
    }


}
