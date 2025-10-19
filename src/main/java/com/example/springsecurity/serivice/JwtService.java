package com.example.springsecurity.serivice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.codec.Decoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final String SECRET="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDC1z6b0bYKT9Fv+7F2l0g/3pXr";
    private  String secretekey;
    public JwtService(){
        this.secretekey=generateSecreteKey();
    }
    public String generateSecreteKey(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secrekey= keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secrekey.getEncoded());

        }catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("error generating key",e);

        }
    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(getKey(), SignatureAlgorithm.HS256).
                compact();
    }
    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretekey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

