package com.example.demo.utils;

import java.util.List;

import io.jsonwebtoken.Claims;

public class Auth {

  public static boolean validateUser(String token, List<String> userTypes) {
    try {
      Claims jwt = Jwt.decodeToken(token);
      String type = jwt.get("type", String.class);

      return userTypes.contains(type);
    } catch (Exception e) {
      return false;
    }
  }
}
