package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Notification;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullNotificationException;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.notification.NotificationCheckService;
import Baetube_backEnd.service.notification.NotificationDeleteService;
import Baetube_backEnd.service.notification.NotificationSelectService;

@RestController
public class RestNotificationController
{
	@Autowired
	private NotificationDeleteService notificationDeleteService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	@Autowired
	private NotificationSelectService notificationSelectService;
	@Autowired
	private NotificationCheckService notificationCheckService;
	
	@PostMapping("/api/notification/delete")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deleteNotification(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid Long contentsId, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			Notification notification = new Notification(user.getUserId(), contentsId);
			notificationDeleteService.delete(notification);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullNotificationException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/notification/select/video")
	public ResponseEntity<Object> getVideoNotification(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			List<HashMap<String, String>> videoNotifications = notificationSelectService.selectVideoNotifications(user.getUserId());
			
			return ResponseEntity.status(HttpStatus.OK).body(videoNotifications);
		} 
		catch (NullNotificationException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/notification/select/community")
	public ResponseEntity<Object> getCommunityNotification(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			List<HashMap<String, String>> communityNotifications = notificationSelectService.selectCommunityNotifications(user.getUserId());
			
			return ResponseEntity.status(HttpStatus.OK).body(communityNotifications);
		} 
		catch (NullNotificationException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/notification/select/new")
	public ResponseEntity<Object> getNewUserNotification(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			List<Notification> communityNotifications = notificationSelectService.selectNewUserNotification(user.getUserId());
			
			return ResponseEntity.status(HttpStatus.OK).body(communityNotifications);
		} 
		catch (NullNotificationException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/notification/check")
	public ResponseEntity<Object> checkNotifications(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{                                              
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			notificationCheckService.checkNotifications(user.getUserId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullNotificationException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
