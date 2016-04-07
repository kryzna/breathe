package com.rgp.breathe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${configuration.name}")
    void setProjectName(String projectName) {

    }

    @Autowired
    void setEnvironment(Environment env) {
        System.out.println("env = " + env.getProperty("configuration.name"));
    }

    @Autowired
    void setConfigurationProjectProperties(ConfigurationProjectProperties cp) {
        System.out.println("cp = " + cp.getName());
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        logger.info("Application Started");
    }
}

@Component
@ConfigurationProperties("configuration")
class ConfigurationProjectProperties {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
