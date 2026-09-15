package crudApp.config;

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
public class PersistenceConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/CRUD",
                        "root", "root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;

    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("crudApp.model"); // указываем где искать сущьность
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());    // указываем кто реализует Java Persistence API (hibernate)
        Properties properties = new Properties(); // создаем обьект для конфигурации Hibernate
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // укажим диалект для Hibernate
        properties.setProperty("hibernate.show_sql", "true");       // видеть SQL-запросы в консоли
        properties.setProperty("hibernate.hbm2ddl.auto", "update");  // автосоздание/обновление таблиц по Entity
        emf.setJpaProperties(properties);
        return emf;

    }
    @Bean
    public JpaTransactionManager transactionManager (LocalContainerEntityManagerFactoryBean emf){
        JpaTransactionManager jtm = new JpaTransactionManager(emf.getObject());
        return jtm;
    }


}
