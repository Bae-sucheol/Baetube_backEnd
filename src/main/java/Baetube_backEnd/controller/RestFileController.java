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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.service.file.FileUploadService;

@RestController
public class RestFileController
{
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/api/file/upload")
	public ResponseEntity<Object>  deleteContents(@RequestBody MultipartHttpServletRequest request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			try
			{
				System.out.println("파일 업로드 요청이 들어왔습니다.");
				fileUploadService.upload(request);
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
