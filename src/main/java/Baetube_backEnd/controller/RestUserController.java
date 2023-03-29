package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.checkerframework.checker.units.qual.cd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.dto.ChangePasswordRequest;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.ExpiredAccessTokenException;
import Baetube_backEnd.exception.ExpiredRefreshTokenException;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.jwt.JwtAccessTokenService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
import Baetube_backEnd.service.user.ChangePasswordService;
import Baetube_backEnd.service.user.UserLoginService;
import Baetube_backEnd.service.user.UserRegisterService;
import Baetube_backEnd.service.user.UserUnregisterService;
import Baetube_backEnd.service.user.UserUpdateService;
import Baetube_backEnd.ErrorResponse;

@RestController
public class RestUserController
{
	
	@Autowired
	private UserRegisterService userRegisterService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserUnregisterService userUnregisterService;
	@Autowired
	private ChangePasswordService changePasswordService;
	@Autowired
	private UserUpdateService userUpdateService;
	@Autowired
	private JwtAccessTokenService JwtAccessTokenService;
	@Autowired
	private PlaylistInsertService playlistInsertService;
	@Autowired
	private ChannelInsertService channelInsertService;

	@PostMapping("/api/user/regist")
	public ResponseEntity<Object> newUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			System.out.println("요청이 들어왔습니다.");
			User newUser = userRegisterService.regist(request);
			
			// 회원 가입 후 기본적으로 채널을 하나 삽입해야한다.
			Channel newChannel = new Channel(newUser.getUserId(), newUser.getName());
			channelInsertService.insertChannel(newChannel);
			
			// 채널 생성 후 해당 채널의 id를 받아와 좋아요를 누른 동영상, 나중에 볼 동영상 재생목록을 추가해야 한다.
			// visible(public)을 3으로 주어 3이라는 visible(public)은 삭제할 수 없도록 한다.
			Playlist playlistLikeVideo = new Playlist(newChannel.getChannelId(), "좋아요를 누른 동영상", 3, "");
			Playlist playlistAfterWatching = new Playlist(newChannel.getChannelId(), "나중에 볼 동영상", 3, "");
			
			playlistInsertService.insert(playlistLikeVideo);
			playlistInsertService.insert(playlistAfterWatching);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
			
	@PostMapping("/api/user/login")
	public ResponseEntity<Object> loginUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			System.out.println("요청이 들어왔습니다.");
			TokenInfo tokenInfo = userLoginService.login(request);
			System.out.println("토큰 정보 : " + tokenInfo.getAccessToken());
			return ResponseEntity.status(HttpStatus.OK).body(tokenInfo);
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (WrongIdPasswordException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
	@PostMapping("/api/user/unregist")
	public ResponseEntity<Object> unRegistUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			userUnregisterService.unRegist(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/change_password")
	public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			changePasswordService.changePassword(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (WrongIdPasswordException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/update")
	public ResponseEntity<Object> userUpdate(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			userUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/test")
	public ResponseEntity<Object> usertest(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			System.out.println("요구가 도착했습니다.");
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (ExpiredAccessTokenException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("ExpiredAccessTokenException");
		}

	}
	
	@PostMapping("/api/generate/access")
	public ResponseEntity<Object> generateAccessToken(@RequestBody TokenInfo request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			TokenInfo tokenInfo = JwtAccessTokenService.generateToken(request);
			return ResponseEntity.status(HttpStatus.OK).body(tokenInfo);
		} 
		catch (ExpiredRefreshTokenException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).header("Exception", "ExpiredRefreshTokenException").build();
		}

	}
	
	@GetMapping("/api/members")
	public List<User> member()
	{
		ArrayList<User> list = new ArrayList<>();
		list.add(new User(1, "test@naver.com", "1234", "test", 1, null, "1234", "1234", "1234", null));
		list.add(new User(1, "test2@naver.com", "1234", "test2", 1, null, "1234", "1234", "1234", null));
		list.add(new User(1, "test3@naver.com", "1234", "test3", 1, null, "1234", "1234", "1234", null));
		
		return list;
	}
	
	
}
