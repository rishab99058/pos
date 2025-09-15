package com.vermau2k01053.pos_System.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.JWT_SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication) {
        Collection<? extends GrantedAuthority>
                authorities =
                authentication.getAuthorities();

        String roles = populateAuthorities(authorities);

        return Jwts.builder().issuedAt(new Date()).
                expiration(new Date(new Date().getTime()+86400000))
                .claim("email",authentication.getName())
                .claim("authorities", roles)
                .signWith(secretKey)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            authoritiesSet.add(grantedAuthority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }


    public String getEmailFromToken(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claims.get("email").toString();
    }
}
