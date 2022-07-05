package com.template.model.dto.response;

import com.template.model.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String name;
    private Role role;
}
