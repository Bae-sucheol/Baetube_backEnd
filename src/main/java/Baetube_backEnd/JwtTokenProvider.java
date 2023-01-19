package Baetube_backEnd;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import Baetube_backEnd.dto.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@PropertySource("classpath:application.properties")
public class JwtTokenProvider
{
	private final Key key;
	@Value("${jwt.secret}")
	private static String secret;
	private static final int day = 24 * 60 * 60 * 1000; // 하루
	private static final int accessTokenTime = 1; // 1일
	private static final int refreshTokenTime = 60; // 60일 
	
	public JwtTokenProvider()
	{
		byte keyBytes[] = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	// 유저 정보로 Access, Refresh 토큰을 생성
	public TokenInfo generateToken(Authentication authentication)
	{
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		long now = (new Date()).getTime();
		
		// Access Token 생성
		Date accessTokenExpiresIn = new Date(now + day * accessTokenTime);
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("auth", authorities)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		// Refresh Token 생성
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(now + day * refreshTokenTime))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return new TokenInfo("Bearer", accessToken, refreshToken);
	}
	
	// JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) 
    {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
 
        if (claims.get("auth") == null) 
        {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
 
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
 
        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    
 // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) 
    {
        try 
        {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } 
        catch (SecurityException | MalformedJwtException e) 
        {
        	e.printStackTrace();
        }
        catch (ExpiredJwtException e)
        {
        	e.printStackTrace();
        } 
        catch (UnsupportedJwtException e) 
        {
        	e.printStackTrace();
        } 
        catch (IllegalArgumentException e) 
        {
        	e.printStackTrace();
        }
        
        return false;
    }
 
    private Claims parseClaims(String accessToken) 
    {
        try 
        {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } 
        catch (ExpiredJwtException e) 
        {
            return e.getClaims();
        }
    }
}
