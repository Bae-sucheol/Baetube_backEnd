package Baetube_backEnd.controller;

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;
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
	
	public void setUserRegisterService(UserRegisterService userRegisterService)
	{
		this.userRegisterService = userRegisterService;
	}

	@PostMapping("/api/user/regist")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> newUser(@RequestBody @Valid User request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			Integer newUserId = userRegisterService.regist(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
			
	@PostMapping("/api/user/login")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> loginUser(@RequestBody @Valid User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			userLoginService.login(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/unregist")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> unRegistUser(@RequestBody @Valid User request, Errors errors, HttpServletResponse response) throws IOException
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
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/change_password")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> changePassword(@RequestBody @Valid ChangePasswordRequest request, Errors errors, HttpServletResponse response) throws IOException
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
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/update")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> userUpdate(@RequestBody @Valid User request, Errors errors, HttpServletResponse response) throws IOException
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
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/user/all")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> all(Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
