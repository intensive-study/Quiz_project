package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    Environment env;

    public JwtUtil(Environment env){
        this.env = env;
    }

    private boolean isJwtValid(String jwt){

        boolean result = true;
        String subject = null;
        try{
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt.trim()).getBody()
                    .getSubject();
        } catch(Exception ex){
            result = false;
        }

        if(subject == null || subject.isEmpty()){
            result = false;
        }

        return result;
    }
}
