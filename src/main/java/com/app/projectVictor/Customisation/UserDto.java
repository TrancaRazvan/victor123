package com.app.projectVictor.Customisation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Clasa asta tine de login
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//Initial nu au mers anotarile. Tb vazut daca sunt ok acuma.
public class UserDto{
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
