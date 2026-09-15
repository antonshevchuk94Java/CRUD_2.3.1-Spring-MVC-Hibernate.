package crudApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"crudApp.service", "crudApp.dao"})
public class AppConfig {
}
