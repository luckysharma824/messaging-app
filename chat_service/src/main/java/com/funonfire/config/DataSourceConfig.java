package com.funonfire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {

    private final Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    /*@Bean
    protected DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("datasource.driver.class-name"));
        hikariConfig.setJdbcUrl(env.getProperty("datasource.url"));
        hikariConfig.setUsername(env.getProperty("datasource.username"));
        hikariConfig.setPassword(env.getProperty("datasource.password"));
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setMaxLifetime(200);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    protected PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

}
