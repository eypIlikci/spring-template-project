package com.template.service.impl;

import com.template.auth.AuthService;
import com.template.dao.repository.UserRepository;
import com.template.error.CustomError;
import com.template.model.dto.request.UserRegisterRequest;
import com.template.model.dto.request.UserUpdatePasswordRequest;
import com.template.model.dto.response.GenericResponse;
import com.template.model.dto.response.UserResponse;
import com.template.model.enums.ErrorConstants;
import com.template.model.enums.GenericConstants;
import com.template.model.mapper.UserMapper;
import com.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new CustomError(ErrorConstants.USED_EMAIL);
        });
        var user=userMapper.map(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);
        var response=userMapper.map(user);
        return response;
    }

    @Override
    public GenericResponse updatePassword(UserUpdatePasswordRequest request) {
        var auth=authService.auth();
        if (!passwordEncoder.matches(auth.getPassword(),request.getOldPassword())){
            throw new CustomError(ErrorConstants.UPDATE_ERROR);
        }
        auth.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(auth);
        return new GenericResponse(GenericConstants.UPDATED);
    }
}
