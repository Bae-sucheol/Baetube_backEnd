package Baetube_backEnd.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;

@RestController
public class RestHlsController
{
	
	private static final String basePath = Paths.get("G:", "baetube", "video").toString();
	
	@GetMapping("/hls/{fileName}/{fileName}.m3u8")
	public ResponseEntity<Resource> getVideoHlsM3U8(@PathVariable String fileName) throws IOException
	{
		                                                
		try
		{	
			String fileFullPath = Paths.get(basePath, fileName, fileName + ".m3u8").toString();
			Resource resource = new FileSystemResource(fileFullPath); 
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".m3u8");
			headers.setContentType(MediaType.parseMediaType("application/vnd.apple.mpegurl"));
			
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		} 
		catch (Exception e)
		{
			
			return new ResponseEntity<Resource>(null, null, HttpStatus.CONFLICT);
		}
	
	}
	
	@GetMapping("/hls/{fileName}/{tsName}.ts")
	public ResponseEntity<Resource> getVideoHlsTs(@PathVariable String fileName, @PathVariable String tsName) throws IOException
	{
		
		try
		{	
			String fileFullPath = Paths.get(basePath, fileName, tsName + ".ts").toString();
			Resource resource = new FileSystemResource(fileFullPath); 
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + tsName + ".ts");
			headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
			
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		} 
		catch (Exception e)
		{
			
			return new ResponseEntity<Resource>(null, null, HttpStatus.CONFLICT);
		}
	
	}
}
