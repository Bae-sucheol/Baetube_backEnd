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
	// �׽�Ʈ������ ª�� 
	private static final int day = 60 * 1000; // 1��
	private static final int accessTokenTime = 1; // 5��
	private static final int refreshTokenTime = 60; // 10�� 
	/*
	private static final int day = 24 * 60 * 60 * 1000; // �Ϸ�
	private static final int accessTokenTime = 1; // 1��
	private static final int refreshTokenTime = 60; // 60�� 
	*/
	public JwtTokenProvider()
	{
		byte keyBytes[] = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	// ���� ������ Access, Refresh ��ū�� ����
	public TokenInfo generateToken(Authentication authentication)
	{
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		long now = (new Date()).getTime();
		
		// Access Token ����
		String accessToken = generateAccessToken(now, authentication, authorities);
		
		// Refresh Token ����
		String refreshToken = generateRefreshToken(now, authentication, authorities);
		
		return new TokenInfo("Bearer", accessToken, refreshToken);
	}
	
	// ���� ������ AccessToken ����
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
	
	// ���� ������ RefreshToken ���� 
	public String generateRefreshToken(long now, Authentication authentication, String authorities)
	{
		Date refreshTokenExpriesIn = new Date(now + day * refreshTokenTime);
		
		String refreshToken = Jwts.builder()
				.setExpiration(refreshTokenExpriesIn)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return refreshToken;
	}
	
	// JWT ��ū�� ��ȣȭ�Ͽ� ��ū�� ����ִ� ������ ������ �޼���
    public Authentication getAuthentication(String token) throws ExpiredJwtException
    {
        // ��ū ��ȣȭ
        Claims claims = validateToken(token);
        
        if (claims.get("auth") == null) 
        {
            throw new RuntimeException("���� ������ ���� ��ū�Դϴ�.");
        }
 
        // Ŭ���ӿ��� ���� ���� ��������
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
 
        // UserDetails ��ü�� ���� Authentication ����
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    
    // ��ū ������ �����ϴ� �޼���
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
    
    // ��ū�� ��ȿ�ϴ� �ƴϴ� ������ �Ľ��ؼ� �����͸� �Ѱ��ִ� �޼ҵ�.
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
