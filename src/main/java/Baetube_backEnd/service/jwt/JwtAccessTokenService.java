package Baetube_backEnd.service.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;
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

	@Transactional
	public TokenInfo generateToken(TokenInfo request) throws ExpiredRefreshTokenException
	{
		// 리프레시 토큰을 검증한다.
		validateRefreshToken(request);
		
		// 검증이 성공했다면 AccessToken을 생성한다.
		long now = (new Date()).getTime();

		Authentication authentication = jwtTokenProvider.getAuthentication(request.getAccessToken());

		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		String accessToken = jwtTokenProvider.generateAccessToken(now, authentication, authorities);
		
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setGrantType("Bearer");
		tokenInfo.setAccessToken(accessToken);

		return tokenInfo;
	}

	// refreshToken을 가져오고 검증하는 메소드
	private void validateRefreshToken(TokenInfo tokenInfo) throws ExpiredRefreshTokenException
	{
		// accessToken에서 정보를 추출한다.
		Claims claims = jwtTokenProvider.parseToken(tokenInfo.getAccessToken());
		
		// 유저 이메일을 가져와 UserMapper를 통해 유저의 refreshToken을 가져온다.
		String email = claims.getSubject();
		String refreshToken = userMapper.selectRefreshToken(email);

		// refreshToken이 존재하지 않거나 일치하지 않다면 refreshToken 만료 예외 투척.
		if (refreshToken == null || !refreshToken.equals(tokenInfo.getRefreshToken()))
		{
			throw new ExpiredRefreshTokenException();
		}
		
		try
		{
			// 검증을 실행 검증이 실패했다면 익셉션
			jwtTokenProvider.validateToken(refreshToken);
		} 
		catch (ExpiredJwtException e) // 리프레시 토큰이 만료되었다면.
		{
			// refreshToken이 만료되었다면 refreshToken 만료 예외 투척.
			throw new ExpiredRefreshTokenException();
		}

	}

}
