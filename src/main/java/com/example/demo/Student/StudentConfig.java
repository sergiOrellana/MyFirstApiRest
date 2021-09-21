package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student Maria = new Student(
                    "Maria",
                    "Maria.jamal@gmail.com",
                    LocalDate.of(1984, Month.JANUARY, 15)
                    );

            Student Alex = new Student(
                    "Alex",
                    "Alex@gmail.com",
                    LocalDate.of(1995, Month.MAY, 7));

            repository.saveAll(List.of(Alex, Maria));
        };
    }
}
