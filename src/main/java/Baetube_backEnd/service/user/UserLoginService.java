package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.UserMapper;


public class UserLoginService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Transactional
	public TokenInfo login(User user) throws BadCredentialsException
	{
		// Authentication 객체 생성
		// 인증 여부(authenticated 값)는 false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		// 실제 검증
		// authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 인증 정보를 기반으로 토큰 생성
		TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		
		// 생성된 refreshToken은 db에 저장한다.
		userMapper.updateRefreshToken(user.getEmail(), tokenInfo.getRefreshToken());
		
		return tokenInfo;
	}
	
	
	/*
	@Transactional
	public User login(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		if(!user.getPassword().equals(request.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
		
		return user;
	}
	*/
}
