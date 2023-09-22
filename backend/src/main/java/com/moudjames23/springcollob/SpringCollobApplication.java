package com.moudjames23.springcollob;

import com.moudjames23.springcollob.dtos.StudentDto;
import com.moudjames23.springcollob.entities.Student;
import com.moudjames23.springcollob.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SpringCollobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCollobApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return  new ModelMapper();
    }

    @Bean
    public CommandLineRunner seeder(final StudentRepository studentRepository)
    {
        return args -> {
            Student  student1 = Student.builder()
                    .name("Lebron James")
                    .email("lbj@gmail.com")
                    .age(23)
                    .build();

            Student  student2 = Student.builder()
                    .name("Kobe Bryant")
                    .email("kobe@gmail.com")
                    .age(34)
                    .build();

            Student  student3 = Student.builder()
                    .name("Allen Iverson")
                    .email("i3@gmail.com")
                    .age(3)
                    .build();

            List<Student> students = Arrays.asList(student1, student2, student3);

            studentRepository.saveAll(students);
        };
    }

    /*@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }*/
}
