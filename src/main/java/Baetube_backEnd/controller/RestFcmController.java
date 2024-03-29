package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.service.fcm.FCMSaveService;

@RestController
public class RestFcmController
{
	@Autowired
	private FCMSaveService fcmSaveService;
	
	@PostMapping("/api/fcm/save")
	public ResponseEntity<Object> saveFCMToken(@RequestHeader("Authorization") String bearerToken, @RequestBody String fcmToken, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			fcmSaveService.saveFCMToken(fcmToken, bearerToken);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
