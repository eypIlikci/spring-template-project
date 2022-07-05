package com.template.controller;

import com.template.model.dto.request.UserUpdatePasswordRequest;
import com.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/update/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UserUpdatePasswordRequest request){
        return ResponseEntity.ok(userService.updatePassword(request));
    }
}
