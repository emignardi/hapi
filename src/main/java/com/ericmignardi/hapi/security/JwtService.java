package com.ericmignardi.hapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRET = "630DEB325943FE067B3F88CEB1FFA5B1ABD72A848F28CE7974B615265D69116CEA2C6B117A2F5377C258EECFFEC5BB3B99FAA0FDE2E609C34DFDFA43B1CFE707";
    private static final long EXPIRATION = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(EXPIRATION)))
                .signWith(decodeSecret())
                .compact();
    }

    private SecretKey decodeSecret() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(decodeSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
