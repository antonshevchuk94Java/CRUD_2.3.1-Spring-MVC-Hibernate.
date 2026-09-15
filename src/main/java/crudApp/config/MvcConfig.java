package crudApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("crudApp.controller")
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public SpringResourceTemplateResolver templateResolver(){ // 1. Настраиваем сам резолвер шаблонов
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/pages/");          // папка с шаблонами
        templateResolver.setSuffix(".html");                    // расширение файлов
        templateResolver.setTemplateMode(TemplateMode.HTML);    // режим шаблонизатора
        templateResolver.setCharacterEncoding("UTF-8");         // кодировка
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {              // 2. Настраиваем движок шаблонов (Template Engine)
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){                // 3. Настраиваем ViewResolver для интеграции со Spring MVC
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

}
