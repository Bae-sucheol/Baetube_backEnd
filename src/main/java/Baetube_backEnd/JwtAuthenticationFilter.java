package Baetube_backEnd;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.exception.ExpiredAccessTokenException;
import Baetube_backEnd.mapper.UserMapper;
import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter 
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider)
	{
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		// request Header에서 토큰 추출
		String token = resolveToken(request);

		try
		{
			// validateToken 으로 토큰 유효성 검사
			if(token != null && jwtTokenProvider.validateToken(token) != null)
			{
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} 
		catch (ExpiredJwtException e) // accessToken이 만료되었을 경우.
		{
			// 헤더에 엑세스 토큰 만료 메시지를 추가하여 보낸다.
			response.setHeader("Exception", "ExpiredAccessToken");
		}

		filterChain.doFilter(request, response);
	}
	
	// Request Header 에서 토큰 정보 추출
	private String resolveToken(HttpServletRequest request)
	{
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer"))
		{
			return bearerToken.substring(7);
		}
		
		return null;
	}
	
}
