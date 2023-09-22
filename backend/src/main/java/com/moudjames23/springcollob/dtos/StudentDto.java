package com.moudjames23.springcollob.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private long id;

    @NotBlank(message = "student's name is required")
    @Size(min = 5, max = 200)
    private String name;

    @NotBlank(message = "Student'mail is required")
    @Email
    private String email;

    @NotNull(message =  "Student's age is required")
    @Min(10)
    @Max(70)
    private int age;
}
