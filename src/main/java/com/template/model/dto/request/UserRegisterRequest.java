package com.template.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterRequest {
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 6,max = 16)
    private String password;
    @NotNull
    @NotEmpty
    private String name;
}
