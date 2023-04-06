package Baetube_backEnd;

import java.security.Key;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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
	//@Value("${jwt.secret}")
	private static String secret = "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa";
	// 테스트용으로 짧게 
	private static final int day = 60 * 1000; // 1분
	private static final int accessTokenTime = 1; // 5분
	private static final int refreshTokenTime = 60; // 10분 
	/*
	private static final int day = 24 * 60 * 60 * 1000; // 하루
	private static final int accessTokenTime = 1; // 1일
	private static final int refreshTokenTime = 60; // 60일 
	*/
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
		String accessToken = generateAccessToken(now, authentication, authorities);
		
		// Refresh Token 생성
		String refreshToken = generateRefreshToken(now, authentication, authorities);
		
		return new TokenInfo("Bearer", accessToken, refreshToken);
	}
	
	// 유저 정보로 AccessToken 생성
	public String generateAccessToken(long now, Authentication authentication, String authorities)
	{
		Date accessTokenExpriesIn = new Date(now + day * accessTokenTime);
		
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("auth", authorities)
				.setExpiration(accessTokenExpriesIn)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return accessToken;
	}	
	
	// 유저 정보로 RefreshToken 생성 
	public String generateRefreshToken(long now, Authentication authentication, String authorities)
	{
		Date refreshTokenExpriesIn = new Date(now + day * refreshTokenTime);
		
		String refreshToken = Jwts.builder()
				.setExpiration(refreshTokenExpriesIn)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return refreshToken;
	}
	
	// JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String token) throws ExpiredJwtException
    {
        // 토큰 복호화
        Claims claims = validateToken(token);
        
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
    public Claims validateToken(String token) throws ExpiredJwtException
    {
	
        try 
        {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } 
        catch (SecurityException | MalformedJwtException e) 
        {
        	e.printStackTrace();
        }
        catch (ExpiredJwtException e)
        {
        	throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        	//e.printStackTrace();
        } 
        catch (UnsupportedJwtException e) 
        {
        	e.printStackTrace();
        } 
        catch (IllegalArgumentException e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
    
    // 토큰이 유효하던 아니던 무조건 파싱해서 데이터를 넘겨주는 메소드.
    public Claims parseToken(String token) throws ExpiredJwtException
    {
    	try 
        {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } 
        catch (ExpiredJwtException e)
        {
        	return e.getClaims();
        } 
    }
    
}
