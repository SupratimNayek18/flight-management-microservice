package com.spring.apigateway.filter;

import com.spring.apigateway.exception.AuthorizationHeaderMissingException;
import com.spring.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {

            if(validator.isSecured.test(exchange.getRequest())){

                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("JWT Token missing in header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String jwtToken = null;

                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    jwtToken = authHeader.substring(7);
                }

                try{
                    jwtUtil.validateToken(jwtToken);
                }
                catch (Exception e){
                    throw new RuntimeException("Unauthorized Access");
                }
            }

            return chain.filter(exchange);

        });

    }

    public static class Config{

    }

}
