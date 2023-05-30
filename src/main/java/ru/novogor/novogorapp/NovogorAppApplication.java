package ru.novogor.novogorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class NovogorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovogorAppApplication.class, args);
    }

}
