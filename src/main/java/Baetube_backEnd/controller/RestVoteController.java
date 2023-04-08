package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.checkerframework.checker.units.qual.cd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.DuplicateVoteException;
import Baetube_backEnd.exception.DuplicateVoteOptionException;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.vote.CancelVoteOptionService;
import Baetube_backEnd.service.vote.CastVoteOptionService;
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
	@Autowired
	private CastVoteOptionService castVoteOptionService;
	@Autowired
	private CancelVoteOptionService cancelVoteOptionService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
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
			HashMap<String , String> result = voteInsertService.insert(request);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		catch (DuplicateVoteException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 투표가 등록되어 있는 게시글입니다.");
		}
		
	}
	
	@PostMapping("/api/vote/insert/option")
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
	
	@PostMapping("/api/vote/insert/option/multi")
	public ResponseEntity<Object> insertVoteOptionMulti(@RequestBody @Valid List<Vote> request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			System.out.println("요청이 들어왔습니다.");
			System.out.println("size : " + request.size());
			voteInsertOptionMultiService.insertOptionMulti(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateVoteOptionException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/cast/{channelSequence}")
	public ResponseEntity<Object> castVoteOption(@PathVariable Integer channelSequence, @RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			// Vote request객체에 있는 communityId 속성은 해당 메소드에서 channelId로 사용하기 때문에 
			// channelId를 조회하여 다시 적용한다.
			Channel channel = jwtTokenDataExtractService.getChannelData(response, channelSequence);
			request.setCommunityId(channel.getChannelId());
			castVoteOptionService.castVoteOption(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateVoteException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/vote/cancel/{channelSequence}")
	public ResponseEntity<Object> cancelVoteOption(@PathVariable Integer channelSequence, @RequestBody @Valid Vote request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			// Vote request객체에 있는 communityId 속성은 해당 메소드에서 channelId로 사용하기 때문에 
			// channelId를 조회하여 다시 적용한다.
			Channel channel = jwtTokenDataExtractService.getChannelData(response, channelSequence);
			request.setCommunityId(channel.getChannelId());
			cancelVoteOptionService.cancelVoteOption(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateVoteException e)
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
	
	@PostMapping("/api/vote/delete/option")
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
	
	@PostMapping("/api/vote/delete/option/multi")
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
	
	@PostMapping("/api/vote/update/option")
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
	
	@GetMapping("/api/vote/select/option/{voteId}")
	public ResponseEntity<Object> selectVoteOptions(@PathVariable Integer voteId, HttpServletResponse response) throws IOException
	{                                           
		try
		{
			List<Vote> voteOptionList = VoteSelectOptionService.select(voteId);
			return ResponseEntity.status(HttpStatus.OK).body(voteOptionList);
		} 
		catch (NullVoteException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	
}
