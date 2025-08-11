package com.boot.DyaanSetu.service.impl;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public String generateToken(String email,String role) {
		
		Map<String, Object> claim=new HashMap<>();
		claim.put("role", role);
		
		return Jwts.builder()
				.setClaims(claim)
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +1000*60*30))
				.signWith(getKey(),SignatureAlgorithm.HS256).compact();
		
	}
	
	private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lbmluZ1B1cnBvc2VzMTIzNDU2Nzg=";

	private Key getKey() {
		
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
//	public String generateSecretKey() {
//		
//		try {
//			
//			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
//			SecretKey secretKey=keyGen.generateKey();
//			System.out.println("Secret Key : "+secretKey.toString());
//			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//			
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException("Error Generating Secret Key : "+e);
//		}
//		
//	}

	
	public String extractRole(String token) {
		return extractClaim(token, claim -> claim.get("role",String.class));
	}
	
	public String extractUserName(String token) {
		
		return extractClaim(token , Claims::getSubject);
		
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
	    return Jwts
	        .parser()
	        .setSigningKey(getKey())
	        .build()
	        .parseClaimsJws(token)
	        .getBody();
	}


	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}

	
	
}
