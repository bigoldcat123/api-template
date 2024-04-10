package com.example.demo;

import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	public static void main(String[] args) {
		SecretKey key = Jwts.SIG.HS256.key().build();
		System.out.println(key.getFormat());	
		String jws = Jwts.builder().subject("Joe").signWith(key).compact();
		System.out.println(jws);
		System.out.println(Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject());
		System.out.println( Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe"));
	}

}
