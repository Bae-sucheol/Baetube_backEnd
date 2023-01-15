package Baetube_backEnd.service.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import Baetube_backEnd.FFmpegWrapper;
import Baetube_backEnd.exception.NotSupportUploadException;

public class FileUploadService
{
	private static final String BASE_FILE_PATH = Paths.get("G:", "baetube").toString();
	private static final int IMAGE_SIZE = 64;
	
	public boolean upload(String type, String purpose, String id, MultipartFile request) throws IOException
	{
		
		String baseFolderPath = Paths.get(BASE_FILE_PATH, type).toString();
		
		if(!type.equals("video"))
		{
			baseFolderPath = Paths.get(baseFolderPath, purpose).toString();
		}
		
		String uuid = UUID.randomUUID().toString();
		String prefix = request.getOriginalFilename().substring(request.getOriginalFilename().lastIndexOf(".") + 1,
				request.getOriginalFilename().length());
		String fileName = uuid + "_" + id;
		String fileNamePrefix = fileName + "." + prefix;
		String destinationPath = Paths.get(baseFolderPath, fileNamePrefix).toString();
		
		File baseFolder = new File(baseFolderPath);
		File storageFile = new File(destinationPath);
		
		try
		{
			if(!baseFolder.exists())
			{
				baseFolder.mkdirs();
			}
			
			InputStream fileStream = request.getInputStream();
			
			if(type.equals("image"))
			{
			
				BufferedImage resizedImage = resize(fileStream);
				ImageIO.write(resizedImage, "jpeg", storageFile);
			}
			else // 동영상 저장
			{
				FileUtils.copyInputStreamToFile(fileStream, storageFile);
				FFmpegWrapper wrapper = new FFmpegWrapper();
				wrapper.convert(destinationPath, fileName);
			}
		
		} 
		catch (IOException e)
		{
			FileUtils.deleteQuietly(storageFile);
			throw new IOException();
		}
		
		return true;
	}
	
	private BufferedImage resize(InputStream fileStream) throws IOException
	{
		BufferedImage inputImage = ImageIO.read(fileStream);
		BufferedImage outputImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, inputImage.getType());
		
		Graphics2D graphics2d = outputImage.createGraphics();
		graphics2d.drawImage(inputImage, 0, 0, IMAGE_SIZE, IMAGE_SIZE, null);
		graphics2d.dispose();
		
		return outputImage;
	}
	
	public void valid(String type, String purpose) throws NotSupportUploadException
	{
		String types[] = {"image", "video"};
		String purposes[] = {"profile", "thumbnail", "arts", "community", "video"};
		
		HashSet<String> typeSet = new HashSet<>(Arrays.asList(types));
		HashSet<String> purposeSet = new HashSet<>(Arrays.asList(purposes));
		
		if(!typeSet.contains(type) || !purposeSet.contains(purpose))
		{
			throw new NotSupportUploadException();
		}
	}
}
