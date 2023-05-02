package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.History;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullHistoryException;
import Baetube_backEnd.service.history.HistoryDeleteService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;

@RestController
public class RestHistoryController
{
	@Autowired
	private HistoryDeleteService historyDeleteService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	@PostMapping("/api/history/delete")
	public ResponseEntity<Object> deleteHistory(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid History request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			request.setUserId(user.getUserId());
			historyDeleteService.deleteHistory(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullHistoryException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
