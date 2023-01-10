package Baetube_backEnd.service.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadService
{
	private static String BASE_FILE_PATH = "G:\\baetube\\";
	
	public boolean upload(MultipartFile request) throws Exception
	{
		
		File storageFile = new File(BASE_FILE_PATH + request.getOriginalFilename());
		
		try
		{
			InputStream fileStream = request.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, storageFile);
		} 
		catch (IOException e)
		{
			FileUtils.deleteQuietly(storageFile);
			e.printStackTrace();
		}
		
		return true;
	}
}
