package Baetube_backEnd.controller;

import java.io.IOException;
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

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.nestedReply.NestedReplyInsertService;
import Baetube_backEnd.service.nestedReply.NestedReplySelectService;
import Baetube_backEnd.service.nestedReply.NestedReplyUpdateService;

@RestController
public class RestNestedReplyController
{	
	@Autowired
	private NestedReplyInsertService nestedReplyInsertService;
	@Autowired
	private NestedReplySelectService nestedReplySelectService;
	@Autowired
	private NestedReplyUpdateService nestedReplyUpdateService;
	
	@PostMapping("/api/nestedreply/insert")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertNestedReply(@RequestBody @Valid NestedReply request, Errors errors, HttpServletResponse response) throws IOException
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
	
	@GetMapping("/api/nestedreply/select")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectNestedReply(@RequestBody @Valid Integer request, Errors errors, HttpServletResponse response) throws IOException
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
	
	@PostMapping("/api/nestedreply/update")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> updateNestedReply(@RequestBody @Valid NestedReply request, Errors errors, HttpServletResponse response) throws IOException
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
