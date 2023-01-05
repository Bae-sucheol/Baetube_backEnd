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
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.searchhistory.SearchHistoryDeleteService;
import Baetube_backEnd.service.searchhistory.SearchHistoryInsertService;
import Baetube_backEnd.service.searchhistory.SearchHistorySelectService;

@RestController
public class RestSearchHistoryController
{
	@Autowired
	private SearchHistoryDeleteService searchHistoryDeleteService;
	@Autowired
	private SearchHistoryInsertService searchHistoryInsertService;
	@Autowired
	private SearchHistorySelectService searchHistorySelectService;
	
	@PostMapping("/api/searchHistory/insert")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertSearchHistory(@RequestBody @Valid SearchHistory request, Errors errors, HttpServletResponse response) throws IOException
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
	
	@PostMapping("/api/searchHistory/delete")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deleteSearchHistory(@RequestBody @Valid SearchHistory request, Errors errors, HttpServletResponse response) throws IOException
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
	
	@GetMapping("/api/searchHistory/select")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectSearchHistory(@RequestBody @Valid Integer request, Errors errors, HttpServletResponse response) throws IOException
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
