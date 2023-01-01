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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateMemberException;
import Baetube_backEnd.service.UserRegisterService;
import Baetube_backEnd.ErrorResponse;



@RestController
public class RestUserController
{
	
	@Autowired
	private UserRegisterService userRegisterService;

	@PostMapping("/api/regist")
	public ResponseEntity<Object> newUser(@RequestBody User register, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			Integer newUserId = userRegisterService.regist(register);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateMemberException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
