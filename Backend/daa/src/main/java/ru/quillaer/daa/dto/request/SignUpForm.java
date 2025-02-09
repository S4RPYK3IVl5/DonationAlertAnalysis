package ru.quillaer.daa.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 6, max = 50)
    private String username;

    @NotBlank
    @Size
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size
    private String password;

}
