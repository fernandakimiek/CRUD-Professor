package com.project.conquer.school;

import com.project.conquer.school.teacher.infrastructure.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
public class EscolaApplication {

    public static void main(String[] args) {

        SpringApplication.run(EscolaApplication.class, args);

        DBConnection dbConnection = new DBConnection();
        dbConnection.criaTabelaInicial();
    }
}
