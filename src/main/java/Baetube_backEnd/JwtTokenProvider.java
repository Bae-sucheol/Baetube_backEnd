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
	private static final int day = 24 * 60 * 60 * 1000; // �Ϸ�
	private static final int accessTokenTime = 1; // 1��
	private static final int refreshTokenTime = 60; // 60�� 
	
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
		Date accessTokenExpiresIn = new Date(now + day * accessTokenTime);
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("auth", authorities)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		// Refresh Token ����
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(now + day * refreshTokenTime))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return new TokenInfo("Bearer", accessToken, refreshToken);
	}
	
	// JWT ��ū�� ��ȣȭ�Ͽ� ��ū�� ����ִ� ������ ������ �޼���
    public Authentication getAuthentication(String accessToken) 
    {
        // ��ū ��ȣȭ
        Claims claims = parseClaims(accessToken);
 
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
