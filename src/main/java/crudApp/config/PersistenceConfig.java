package crudApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement //@EnableTransactionManagement - запускает механизм Spring для @Transaction
@PropertySource("classpath:app.properties") // указываем файл с конфигурацией
public class PersistenceConfig {
    @Bean
    public DataSource dataSource(@Value("${db.url}") String url,
                                 @Value("${db.password}") String pass,
                                 @Value("${db.username}") String username,
                                 @Value("${db.driver}") String driver) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
                username, pass);
        dataSource.setDriverClassName(driver);
        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Value("${hibernate.dialect}") String dialect,
                                                                       @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
                                                                       @Value("${hibernate.show_sql}") String showSql) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("crudApp.model"); // указываем где искать сущьность
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());    // указываем кто реализует Java Persistence API (hibernate)
        Properties properties = new Properties(); // создаем обьект для конфигурации Hibernate
        properties.setProperty("hibernate.dialect", dialect); // укажим диалект для Hibernate
        properties.setProperty("hibernate.show_sql", showSql);       // видеть SQL-запросы в консоли
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);  // автосоздание/обновление таблиц по Entity
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager jtm = new JpaTransactionManager(emf.getObject());
        return jtm;
    }
}
