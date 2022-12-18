package com.example.wms.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password1;

    @NotEmpty(message = "Password checking is required")
    private String password2;

    @NotEmpty(message = "Email is required")
    @Email
    private String email;
}
