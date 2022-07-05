package com.template.service;

import com.template.model.dto.request.UserRegisterRequest;
import com.template.model.dto.request.UserUpdatePasswordRequest;
import com.template.model.dto.response.GenericResponse;
import com.template.model.dto.response.UserResponse;
import com.template.model.enums.GenericConstants;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    GenericResponse updatePassword(UserUpdatePasswordRequest request);
}
