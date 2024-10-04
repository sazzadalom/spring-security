package com.alom.login.jwt;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {
	
	private SecretKey secretKey ;
	
	public JWTService() {
		try {
			secretKey = KeyGenerator.getInstance("HmacSHA256").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
				.and()
				.signWith(secretKey)
				.compact();
	}

	// extract userName from JWT token
	public String extractUsername(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpaired(token));
	}

	private boolean isTokenExpaired(String token) {
		return extractExparation(token).before(new Date());
	}

	private Date extractExparation(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}


}
