package de.tarent.javastarter.springboot;

import de.tarent.javastarter.springboot.greeting.GreetingController;
import de.tarent.javastarter.springboot.greeting.GreetingRepository;
import de.tarent.javastarter.springboot.greeting.SimpleController;
import liquibase.integration.spring.SpringLiquibase;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
public class BasicConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/database/*");
        return registration;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/liquibase/changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
