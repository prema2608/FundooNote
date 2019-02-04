package com.bridgelabz.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator implements TokenGenerator {
   
	public String generateToken(String id) {
			return Jwts.builder().setId(id).claim("roles", "existingUser").setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			
	}

	public String VerifyToken(String id) {

		return null;
	}

}