package org.example.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static  final String SECRET_KEY="hdi8mqndguHW8ivREI4vIx+Wchl1X6APNuhd33aLZJI9Gbewg5ddvMgByCsQYKUzY44MsCtCVnvGpviHa1mb9iGvRwAK9BHXSaRs8yo2GNv1SH/fK0PEzGyweYxGruKgOicNFuD0z3qHKI3cT3pJnc+/hSOM9qpqlBC0VThZQMMMzdSbAEP9KREEIOWXyxZ8799Qjo0XcnoImpNY4rAM10Qs7TYyk5qhmdpPc3DV0nADXbG9CN8z9xE8Kewg2SKgOlKZJ8PjvAxR0YzyAZOzVFah6jlBXu2b2efyeJB2Ipci3SB0Q/VN7iTjNu24uSH/97bmCDVB6tSFO6yAVCdkw6CuJyHmEUIP1zcyHi5iaXI=";
    public String extractUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }
    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
