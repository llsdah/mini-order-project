package com.fastcampus.miniorderproject.config;


import com.fastcampus.miniorderproject.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class MyJwtProvider {

    private final Long expirationTime;
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public MyJwtProvider(@Value("${jwt.token.expirationTime}") Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    // 토큰 생성
    private String createToken(Map<String,Object> claims, String subject){
        // 만료 시간 성절
        Date date = new Date();
        long time = System.currentTimeMillis();
        date.setTime(time+expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(date)
                .signWith(KEY)
                .compact();
    }

    public String generateToken(UserModel userModel){
        Map<String,Object> maps = new HashMap<>();
        return this.createToken(maps,String.valueOf(userModel.getId()));
    }

    public String extractUserId(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return this.extractClaim(token,Claims::getExpiration);
    }

    public boolean validateToken(String token, UserModel userModel){
        String userId = extractUserId(token);
        return userId.equals(userModel.getId()+"") && !this.isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        Date date = this.extractExpiration(token);
        return date.before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> functionClaims){
        Claims claims = extractAllClaims(token);
        return functionClaims.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
