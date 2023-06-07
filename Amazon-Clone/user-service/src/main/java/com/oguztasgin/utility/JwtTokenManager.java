package com.oguztasgin.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {
    private final String secretKey = "#luC}VB>IsC)*>&x**zMqIdD}Pct_%T3w>{9&Zl$tbXZwfF3J+p%iD~o]8-!^`;";

    public Boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java5").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null) return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }
    public Optional<Long> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java5").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            return Optional.empty();
        }
    }
    public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer("Java5").build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT==null){
                return  Optional.empty();
            }
            return  Optional.of(decodedJWT.getClaim("role").asString());
        }catch (Exception exception){
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
