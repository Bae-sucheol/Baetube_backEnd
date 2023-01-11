package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.List;
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
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.DuplicateVoteOptionException;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.service.vote.VoteDeleteOptionMultiService;
import Baetube_backEnd.service.vote.VoteDeleteOptionService;
import Baetube_backEnd.service.vote.VoteDeleteService;
import Baetube_backEnd.service.vote.VoteInsertOptionMultiService;
import Baetube_backEnd.service.vote.VoteInsertOptionService;
import Baetube_backEnd.service.vote.VoteInsertService;
import Baetube_backEnd.service.vote.VoteSelectOptionService;
import Baetube_backEnd.service.vote.VoteUpdateOptionService;
import Baetube_backEnd.service.vote.VoteUpdateService;

@RestController
public class RestVoteController
{
	@Autowired
	private VoteInsertService voteInsertService;
	@Autowired
	private VoteInsertOptionService voteInsertOptionService;
	@Autowired
	private VoteInsertOptionMultiService voteInsertOptionMultiService;
	@Autowired
	private VoteDeleteService voteDeleteService;
	@Autowired
	private VoteDeleteOptionService voteDeleteOptionService;
	@Autowired
	private VoteDeleteOptionMultiService voteDeleteOptionMultiService;
	@Autowired
	private VoteUpdateService voteUpdateService;
	@Autowired
	private VoteUpdateOptionService voteUpdateOptionService;
	@Autowired
	private VoteSelectOptionService VoteSelectOptionService;
	
	@PostMapping("/api/vote/insert")
	public ResponseEntity<Object> insertVote(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteInsertService.insert(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/insert_option")
	public ResponseEntity<Object> insertVoteOption(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteInsertOptionService.insertOption(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateVoteOptionException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/insert_option_multi")
	public ResponseEntity<Object> insertVoteOptionMulti(@RequestBody @Valid List<Vote> request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteInsertOptionMultiService.insertOptionMulti(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateVoteOptionException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/delete")
	public ResponseEntity<Object> deleteVote(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteDeleteService.delete(request.getVoteId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/delete_option")
	public ResponseEntity<Object> deleteVoteOption(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteDeleteOptionService.deleteOption(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/delete_option_multi")
	public ResponseEntity<Object> deleteVoteOptionMulti(@RequestBody @Valid List<Vote> request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteDeleteOptionMultiService.deleteOptionMulti(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/update")
	public ResponseEntity<Object> updateVote(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/update_option")
	public ResponseEntity<Object> updateVoteOption(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			voteUpdateOptionService.updateOption(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/vote/select_vote_options")
	public ResponseEntity<Object> selectVoteOptions(@RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			VoteSelectOptionService.select(request.getVoteOptionId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVoteException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
