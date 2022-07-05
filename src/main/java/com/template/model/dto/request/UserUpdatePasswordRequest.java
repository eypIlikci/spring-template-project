package com.template.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdatePasswordRequest {
    @NotNull
    @Size(max = 16,min = 6)
    private String newPassword;
    @NotNull
    @NotEmpty
    private String oldPassword;
}
