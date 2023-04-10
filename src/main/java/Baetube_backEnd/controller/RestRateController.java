package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.rate.RateService;

@RestController
public class RestRateController
{
	@Autowired
	private RateService rateService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	@PostMapping("/api/rate/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> rateContents(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Rate request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			request.setChannelId(channel.getChannelId());
			Contents result = rateService.rate(request);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		catch (NullRateResultException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
