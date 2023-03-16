package com.socmed.socmed.modules.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {


    private static final String SECRET_KEY = "743777217A25432A462D4A404E635266556A586E3272357538782F413F442847";
    private static final String REFRESHER_SECRET_KEY = "4A404E635266556A586E3272357538782F413F442A472D4B6150645367566B59";

    public String extractUsername(String token) {

        String username = extractClaim(token, Claims::getSubject);
        log.info("extracting username " + username);
        return username;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put("roles", userDetails.getAuthorities());
        log.info("generating token................");
        return generateToken(claims, userDetails);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Claims extractClaims, UserDetails userDetails) {


        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        log.info("validating token..............");
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && isTokenExpired(token);
    }
    public boolean isRefreshTokenValid(String refreshToken, UserDetails userDetails) {
        log.info("validating token..............");
        final String username = extractUsername(refreshToken);

        return (username.equals(userDetails.getUsername())) && isTokenExpired(refreshToken);
    }

    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    private Key getRefresherSigningKey(){
        byte[] keyByte = Decoders.BASE64.decode(REFRESHER_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
