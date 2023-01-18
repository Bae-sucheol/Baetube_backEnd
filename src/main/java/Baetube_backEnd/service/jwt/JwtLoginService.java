package Baetube_backEnd.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;

public class JwtLoginService
{
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Transactional
	public TokenInfo login(String email, String password)
	{
		// Authentication 객체 생성
		// 인증 여부(authenticated 값)는 false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		
		// 실제 검증
		// authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 인증 정보를 기반으로 토큰 생성
		TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		
		return tokenInfo;
	}
}
