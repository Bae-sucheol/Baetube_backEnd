package Baetube_backEnd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Object> uploadVideo(@RequestParam String type, @RequestParam String purpose, 
			@RequestParam String uuid, @RequestParam MultipartFile file, HttpServletResponse response) throws IOException
	{
		                                    
		try
		{
			System.out.println("요청이 들어왔습니다.");
			fileUploadService.upload(type, purpose, uuid, file);
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
	
	@GetMapping("/api/image/{purpose}/{uuid}.jpg")
	public ResponseEntity<byte[]> getImage(@PathVariable String purpose, @PathVariable String uuid, HttpServletResponse response) throws IOException
	{
		
		try
		{	
			String filePath = Paths.get("G:", "baetube", "image", purpose, uuid + ".jpg").toString();
	        InputStream in = new FileInputStream(new File(filePath));
	        final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
				
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} 
		catch (Exception e)
		{
			
			return new ResponseEntity<byte[]>(null, null, HttpStatus.CONFLICT);
		}
	
	}
	
}
