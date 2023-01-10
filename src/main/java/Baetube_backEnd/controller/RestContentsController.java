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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullContentsException;
import Baetube_backEnd.service.contents.ContentsDeleteService;

@RestController
public class RestContentsController
{
	@Autowired
	private ContentsDeleteService contentsDeleteService;
	
	@PostMapping("/api/contents/delete")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deleteContents(@RequestBody @Valid Contents request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			contentsDeleteService.deleteContents(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullContentsException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
