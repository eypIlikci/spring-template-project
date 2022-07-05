package com.template.config.helper;

import com.sun.security.auth.UserPrincipal;
import com.template.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class UserSocketHandshake extends DefaultHandshakeHandler {
    @Autowired
    private AuthService authService;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        try {
            String token=request.getHeaders().get("authorization").get(0);
            if (authService.checkToken(token)){
                return new UserPrincipal(token);
            }
        }catch (Exception e){
            logger.warn(e);
        }
        return super.determineUser(request, wsHandler, attributes);
    }
}