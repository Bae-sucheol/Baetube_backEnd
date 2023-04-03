package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Baetube_backEnd.JwtAuthenticationFilter;
import Baetube_backEnd.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	// security 무시 항목
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return (web) -> web.ignoring()
				.mvcMatchers("/favicon.ico")
				.mvcMatchers("/css")
				.mvcMatchers("/js")
				.mvcMatchers("/images")
				.mvcMatchers("/webjars")
				.antMatchers("/hls/**")
				.antMatchers("/api/user/login")
				.antMatchers("/api/user/regist")
				.antMatchers("/api/video/insert");
	}
	
	// security 적용 항목
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		final String WHITELIST[] = new String[] {
			"api/channel/**",
			"api/commmunity/**",
			"api/contents/**",
			"api/file/**",
			"api/history/**",
			"hls/**",
			"api/nestedreply/**",
			"api/playlisy/**",
			"api/rate/**",
			"api/reply/**",
			"api/search_history/**",
			"api/subscribe/**",
			"api/user/**",
			"api/video/**",
			"api/vote/**",
			"api/**"
		};
		
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			.antMatchers(HttpMethod.POST, "/**").permitAll()
			//.antMatchers(HttpMethod.POST, "/hls").permitAll() // 화이트 리스트 허용
			//.antMatchers(HttpMethod.POST, "/api/user/login").permitAll() // 화이트 리스트 허용
			//.antMatchers(HttpMethod.POST, "/api/user/regist").permitAll() // 화이트 리스트 허용
			//.antMatchers(HttpMethod.POST, "/api/generate/access").permitAll() // 화이트 리스트 허용
			.anyRequest().authenticated() // 나머지는 인증 요구
			.and().formLogin().disable()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
