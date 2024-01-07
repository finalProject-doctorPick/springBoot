package com.example.security.jwt.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private List<String> roles = new ArrayList<>();

    public void addRole(String role){
        roles.add(role);
    }
}
