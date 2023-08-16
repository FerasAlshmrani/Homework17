package com.example.homework17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(20) not null check(length(name) >= 4)")
    @Size(min = 4,message = "Name must be more than 4 length")
    private String name;

    @NotEmpty(message ="username should not be empty")
    @Size(min = 4, message = "must be more than 4")
    @Column(columnDefinition = "varchar(20) not null unique check(length(username) >= 4)")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email cannot be null")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @Column(columnDefinition = "varchar(20) not null check (role = 'user' or role = 'admin')")
    @Pattern(regexp = "(admin|user)",message = "it must be user or admin")
    private String role;

    @NotNull(message = "cannot be null")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;
}
