package com.example.demo.config;

import com.example.demo.security.JwtAuthorizationManager;
import com.example.demo.security.authentication.JwtAuthorization;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@EnableWebSocket
@Configuration
public class WsConfiguration implements WebSocketConfigurer {

    private static final Logger log = LoggerFactory.getLogger(WsConfiguration.class);
    @Autowired
    JwtAuthorizationManager jwtAuthorizationManager;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                System.out.println(session.getAttributes());
            }

            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                System.out.println(message.getPayload());
                session.sendMessage(message);
            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                exception.printStackTrace();
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                System.out.println(closeStatus);
            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }
        },"/ws").addInterceptors(new HandshakeInterceptor[]{new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                // TODO 验证WS 是否可靠。。。
                List<String> strings = request.getHeaders().get("Sec-WebSocket-Protocol");
                if (strings != null && strings.size() == 1) {
                    String token = strings.getFirst();
                    JwtAuthorization jwtAuthorization = new JwtAuthorization(token);
                    try {
                        jwtAuthorizationManager.verify(() -> jwtAuthorization, null);
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        attributes.put("id", authentication.getPrincipal());
                        return true;
                    } catch (Exception e) {
                        log.error("Authentication failed", e);
                        return false;
                    }
                }
                log.error("WebSocket 需要 token ！");
                return false;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

            }
        }}).setAllowedOriginPatterns("*");
    }
}
