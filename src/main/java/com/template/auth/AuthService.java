package com.template.auth;

import com.template.dao.repository.TokenRepository;
import com.template.dao.repository.UserRepository;
import com.template.error.CustomError;
import com.template.model.entity.Token;
import com.template.model.entity.User;
import com.template.model.enums.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse authenticate(Credentials credentials){
        var user=userRepository.findByEmail(credentials.getUsername())
                .orElseThrow(() -> new CustomError(ErrorConstants.LOGIN));
        if (!passwordEncoder.matches(credentials.getPassword(),user.getPassword()))
            throw new CustomError(ErrorConstants.LOGIN);

        tokenRepository.findByUserId(user.getId()).ifPresent(token -> tokenRepository.delete(token));

        String t= UUID.randomUUID().toString();
        Token token=new Token();
        token.setToken(t);
        token.setUser(user);
        tokenRepository.save(token);

        AuthResponse response=new AuthResponse();
        response.setAuthId(user.getId());
        response.setToken(token.getToken());

        return response;

    }

    @Transactional
    public UserDetails getUser(String token){
        var tkn=tokenRepository.findById(token).orElseThrow(() -> new CustomError(ErrorConstants.JWT));
        CustomAuth auth=new CustomAuth();
        auth.setUser(tkn.getUser());
        return auth;
    }

    public User auth(){
        CustomAuth userDetails=(CustomAuth) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }

    public boolean checkToken(String token){
        var tkn=  tokenRepository.findById(token).orElse(null);
        if (tkn!=null)
            return true;
        return false;
    }




}
