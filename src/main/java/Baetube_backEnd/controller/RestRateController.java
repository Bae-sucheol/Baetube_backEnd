package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.checkerframework.checker.units.qual.A;
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
import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.rate.RateService;

@RestController
public class RestRateController
{
	@Autowired
	private RateService rateService;
	
	@PostMapping("/api/rate/rate_contents")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> rateContents(@RequestBody @Valid Rate request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			rateService.rate(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullRateResultException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
