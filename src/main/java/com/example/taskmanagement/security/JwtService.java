package com.example.taskmanagement.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.Date;
@Service public class JwtService{
 private final SecretKey key; private final long expiration; private final String issuer,audience;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-ms}") long expiration,@Value("${app.jwt.issuer}") String issuer,@Value("${app.jwt.audience}") String audience){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expiration=expiration;this.issuer=issuer;this.audience=audience;}
 public String create(Long id,String email,String role,String name){Date now=new Date();return Jwts.builder().subject(String.valueOf(id)).claim("email",email).claim("role",role).claim("name",name).issuer(issuer).audience().add(audience).and().issuedAt(now).expiration(new Date(now.getTime()+expiration)).signWith(key).compact();}
 public Claims parse(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
}
