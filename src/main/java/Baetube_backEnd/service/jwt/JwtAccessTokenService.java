package Baetube_backEnd.service.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.ExpiredRefreshTokenException;
import Baetube_backEnd.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtAccessTokenService
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public TokenInfo generateToken(TokenInfo request) throws ExpiredRefreshTokenException
	{
		// �������� ��ū�� �����Ѵ�.
		User user = validateRefreshToken(request);
		
		// ������ �����ߴٸ� AccessToken�� �����Ѵ�.
		long now = (new Date()).getTime();
		
		// Authentication ��ü ����
		// ���� ����(authenticated ��)�� false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
						
		// ���� ����
		// authenticate �ż��尡 ����� �� CustomUserDetailsService ���� ���� loadUserByUsername �޼��尡 ����
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
						
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		String accessToken = jwtTokenProvider.generateAccessToken(now, authentication, authorities);
		
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setGrantType("Bearer");
		tokenInfo.setAccessToken(accessToken);

		return tokenInfo;
	}

	// refreshToken�� �������� �����ϴ� �޼ҵ�
	private User validateRefreshToken(TokenInfo tokenInfo) throws ExpiredRefreshTokenException
	{
		// accessToken���� ������ �����Ѵ�.
		Claims claims = jwtTokenProvider.parseToken(tokenInfo.getAccessToken());
		
		// ���� �̸����� ������ UserMapper�� ���� ������ refreshToken�� �����´�.
		String email = claims.getSubject();
		User user = userMapper.selectByEmail(email);
		String refreshToken = user.getRefreshToken();

		// refreshToken�� �������� �ʰų� ��ġ���� �ʴٸ� refreshToken ���� ���� ��ô.
		if (refreshToken == null || !refreshToken.equals(tokenInfo.getRefreshToken()))
		{
			throw new ExpiredRefreshTokenException();
		}
		
		try
		{
			// ������ ���� ������ �����ߴٸ� �ͼ���
			jwtTokenProvider.validateToken(refreshToken);
		} 
		catch (ExpiredJwtException e) // �������� ��ū�� ����Ǿ��ٸ�.
		{
			// refreshToken�� ����Ǿ��ٸ� refreshToken ���� ���� ��ô.
			throw new ExpiredRefreshTokenException();
		}
		
		return user;

	}

}
