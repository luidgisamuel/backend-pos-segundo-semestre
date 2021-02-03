package com.example.demo.utils;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Jwt {
  private static final String SECRET = "HkLUaX5SjMPyISmyKRnNLmGEKz5QsxqFeIPu/jE1WKOkrAKo5x4LyudmomrgZ2R7a4BjvCTISV534DmXV/mwcQ==";
  private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

  public static Claims decodeToken(String token) {
    return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
  }
}
