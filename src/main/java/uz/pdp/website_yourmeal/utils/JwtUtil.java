package uz.pdp.website_yourmeal.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_HS256_KEY = "34e7f550e2715c958372db43db8f68c0ea686807bf7eab4f22f2ee673df0f929";
    private final Long EXPIRED_DATE = System.currentTimeMillis()+10*60*1000;
    public SecretKey getKeysHs256(){
        return Keys.hmacShaKeyFor(SECRET_HS256_KEY.getBytes());
    }

    public String generateToken(String username){
        return createToken(username,new HashMap<>());
    }
    public String generateToken(String username, Authentication authenticate){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("roles",authenticate.getAuthorities());
        return createToken(username,claims);
    }


    public String createToken(String subject, Map<String ,Object> claims){
        return Jwts.builder()
                .signWith(getKeysHs256(), SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(EXPIRED_DATE))
                .compact();
    }

    public String getSubject(String token){
        Claims jwtBody = getJWTBody(token);
        return jwtBody.getSubject();
    }

    public Date getExpiration(String token){
        Claims jwtBody = getJWTBody(token);
        Date expiration = jwtBody.getExpiration();
        return expiration;
    }

    public Object getClaims(String token,String claimsName){
        Claims jwtBody = getJWTBody(token);
        return jwtBody.get(claimsName);
    }

    public Claims getJWTBody(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getKeysHs256())
                .build();
        return (Claims) parser
                .parse(token)
                .getBody();
    }
}
