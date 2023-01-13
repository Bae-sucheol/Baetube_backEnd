package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.FFmpegWrapper;
import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NotSupportUploadException;
import Baetube_backEnd.service.file.FileUploadService;

@RestController
public class RestFileController
{
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/api/file/upload")
	public ResponseEntity<Object>  deleteContents(@RequestParam String type, @RequestParam String purpose,
			@RequestParam String id, @RequestParam MultipartFile file, HttpServletResponse response) throws IOException
	{
		                                    
		try
		{
			fileUploadService.valid(type, purpose);
			fileUploadService.upload(type, purpose, id, file);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (IOException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (NotSupportUploadException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
