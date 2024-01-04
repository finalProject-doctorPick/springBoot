package com.example.security.jwt.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenizer {

    private final byte[] accessSecret;
    private final byte[] refreshSecret;

    public final static Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30 minutes
    public final static Long REFRESH_TOKEN_EXPIRE_COUNT = 7 * 24 * 60 * 60 * 1000L; // 7 days

    public JwtTokenizer(@Value("${jwt.secretKey}") String accessSecret, @Value("${jwt.refreshKey}") String refreshSecret) {
        this.accessSecret = accessSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: id, email, roles
     *  @return		: String
     * 	@explain	: AccessToken 생성 
     * 
     * */
    public String createAccessToken(Integer id, String email, List<String> roles) {
        return createToken(id, email, roles, ACCESS_TOKEN_EXPIRE_COUNT, accessSecret);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: id, email, roles
     *  @return		: String
     * 	@explain	: RefreshToken 생성
     *  
     * */
    public String createRefreshToken(Integer id, String email, List<String> roles) {
        return createToken(id, email, roles, REFRESH_TOKEN_EXPIRE_COUNT, refreshSecret);
    }


    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: id, email, roles, expire, secretKey
     *  @return		: String
     * 	@explain	: token 생성
     *  
     * */
    private String createToken(Integer id, String email, List<String> roles,
                               Long expire, byte[] secretKey) {
        Claims claims = Jwts.claims().setSubject(email);

        claims.put("roles", roles);
        claims.put("userId", id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expire))
                .signWith(getSigningKey(secretKey))
                .compact();
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: id, email, roles, expire, secretKey
     *  @return		: String
     * 	@explain	: 토큰에서 유저 아이디 얻기
     *  
     * */
    public Integer getUserIdFromToken(String token) {
        String[] tokenArr = token.split(" ");
        token = tokenArr[1];
        System.out.println("************************************************************************");
        System.out.println("getUserIdFromToken 진입 후 tokenArr[1] 값 : " + token);
        System.out.println("************************************************************************");
        
        Claims claims = parseToken(token, accessSecret);
        Integer result = (Integer)claims.get("userId");
        
        System.out.println("************************************************************************");
        System.out.println("return 전 result 값 : " + result);
        System.out.println("************************************************************************");
        
        return result; 
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: accessToken
     *  @return		: Claims
     * 	@explain	: Access Token 분석
     *  
     * */
    public Claims parseAccessToken(String accessToken) {
        return parseToken(accessToken, accessSecret);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: refreshToken
     *  @return		: Claims
     * 	@explain	: Refresh Token 분석
     *  
     * */
    public Claims parseRefreshToken(String refreshToken) {
        return parseToken(refreshToken, refreshSecret);
    }


    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: token, secretKey
     *  @return		: Claims
     * 	@explain	: Token 분석
     *  
     * */
    public Claims parseToken(String token, byte[] secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: secretKey
     *  @return		: Key
     * 	@explain	: SigningKey 얻기
     *  
     * */
    public static Key getSigningKey(byte[] secretKey) {
        return Keys.hmacShaKeyFor(secretKey);
    }
    
}