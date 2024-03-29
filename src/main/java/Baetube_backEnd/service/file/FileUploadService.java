package Baetube_backEnd.service.file;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import Baetube_backEnd.FFmpegWrapper;
import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.exception.NotSupportUploadException;
      
public class FileUploadService
{
	private static final String BASE_FILE_PATH = Paths.get("G:", "baetube").toString();
	private static int ImageResolution[][] = {{64, 64}, {640, 360}, {640, 360}, {640, 360}};
	private int selectedResolution;
	
	public boolean upload(String type, String purpose, String fileName, MultipartFile request) throws IOException, NotSupportUploadException
	{
		/* 요청한 타입, 목적을 검증한다.
		 * 검증이 실패하면 valid 메소드에서 NotSupportUploadException을 발생시킨다.
		 */
		valid(type, purpose);
		
		// 베이스 폴더 path와 파일의 타입(이미지, 동영상) 별 폴더를 조합한 path를 baseFolderPath로 지정.
		String baseFolderPath = Paths.get(BASE_FILE_PATH, type).toString();
		
		// 타입이 video가 아니면 즉 파일이 이미지면 목적에 맞게(프로필 사진, 채널배너, 게시글 이미지) 폴더를 생성하기 위한 path를 생성.
		if(!type.equals("video"))
		{
			baseFolderPath = Paths.get(baseFolderPath, purpose).toString();
		}
		
		/*
		 * 받은 파일의 파일명을 그대로 사용하는 것은 좋지 않다고 판단.
		 * 중복되지 않도록 TimeStamp + Hash를 사용하는 방법도 있지만
		 * uuid를 사용하기로 했음.
		 */
		
		// 확장자를 가져온다.
		//String fileName = UUIDUtil.createUUID();
		String prefix = UUIDUtil.getPrefix(request.getOriginalFilename());
		String fileNamePrefix = fileName + "." + prefix;
		String destinationPath = Paths.get(baseFolderPath, fileNamePrefix).toString();
		
		File baseFolder = new File(baseFolderPath);
		File storageFile = new File(destinationPath);
		
		// baseFolde가 존재하지 않으면 폴더를 생성.
		if(!baseFolder.exists())
		{
			baseFolder.mkdirs();
		}
		
		// multipart file request의 inputStream을 받아온다.
		InputStream fileStream = request.getInputStream();
	
		// 타입이 이미지면 리사이징 후 저장.
		if(type.equals("image"))
		{
			
			
			
			saveImage(fileStream, storageFile);
		}
		else // 파일이 동영상이면 파일을 저장하고 FFmpeg을 사용하여 hls 인코딩.
		{
			saveAndEncodingVideo(fileStream, storageFile, destinationPath, fileName);
		}
		
		return true;
	}
	
	public void deleteImage(String type, String purpose, String fileName) throws IOException
	{
		valid(type, purpose);
		// 베이스 폴더 path와 파일의 타입(이미지, 동영상) 별 폴더를 조합한 path를 baseFolderPath로 지정.
		String baseFolderPath = Paths.get(BASE_FILE_PATH, type).toString();
				
		// 타입이 video가 아니면 즉 파일이 이미지면 목적에 맞게(프로필 사진, 채널배너, 게시글 이미지) 폴더를 생성하기 위한 path를 생성.
		baseFolderPath = Paths.get(baseFolderPath, purpose).toString();
		String fileNamePrefix = fileName + ".jpg";
		String destinationPath = Paths.get(baseFolderPath, fileNamePrefix).toString();
		File selectedImage = new File(destinationPath);
		
		// 파일이 존재한다면 삭제한다.
		if(selectedImage.exists())
		{
			selectedImage.delete();
		}
	}
	
	public void deleteVideo(String fileName) throws IOException
	{
		String path = Paths.get(BASE_FILE_PATH, "video", fileName).toString();
		
		deleteFolder(path);
	}
	
	public void deleteFolder(String path)
	{
		File folder = new File(path);
		
	    try
	    {
			if(folder.exists())
			{
                File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
				
				for (int i = 0; i < folder_list.length; i++) 
				{
				    if(folder_list[i].isFile()) 
				    {
						folder_list[i].delete();
						System.out.println("파일이 삭제되었습니다.");
				    }
				    else 
				    {
						deleteFolder(folder_list[i].getPath()); //재귀함수호출
						System.out.println("폴더가 삭제되었습니다.");
				    }
				    
				    folder_list[i].delete();
				 }
				
			folder.delete(); //폴더 삭제
			
	       }
	   } 
	   catch (Exception e) 
	   {
	    	
		   e.getStackTrace();
	    	
	   }
	}
	
	// 
	private void saveImage(InputStream fileStream, File storageFile) throws IOException
	{
		BufferedImage resizedImage = resize(fileStream);
		ImageIO.write(resizedImage, "jpeg", storageFile);
	}
	
	// 
	private void saveAndEncodingVideo(InputStream fileStream, File storageFile, String destinationPath, String fileName) throws IOException
	{
		try
		{
			// inputStream을 파일로 카피한다.
			FileUtils.copyInputStreamToFile(fileStream, storageFile);
			// ffmepg을 사용하여 변환하는 작업을 시작.
			FFmpegWrapper wrapper = new FFmpegWrapper();
			wrapper.convert(destinationPath, fileName);
			// 작업이 종료되면 받았던 동영상 원본은 제거.
			FileUtils.deleteQuietly(storageFile);
		} 
		catch (IOException e)
		{
			// IOException이 발생하면 파일을 지우고 IOException을 날린다.
			// 날린 Exception은 클래스 밖으로 나가 controller 단에서 처리한다.
			FileUtils.delete(storageFile);
			e.printStackTrace();
		}
		
	}
	
	// 들어온 이미지를 원하는 사이즈로 리사이징.
	// 추후 배너, 프로필, 게시글등 목적에 맞는 이미지 별 사이즈 변환을 지원해야 한다.
	private BufferedImage resize(InputStream fileStream) throws IOException
	{
		int width = ImageResolution[selectedResolution][0];
		int height = ImageResolution[selectedResolution][1];
		
		BufferedImage inputImage = ImageIO.read(fileStream);
		BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
		
		Graphics2D graphics2d = outputImage.createGraphics();
		graphics2d.drawImage(inputImage, 0, 0, width, height, null);
		graphics2d.dispose();
		
		return outputImage;
	}
	
	// 요청한 타입, 목적을 검증하는 메소드
	public void valid(String type, String purpose) throws NotSupportUploadException
	{
		// 가능한 타입과 목적을 배열로 생성.
		String types[] = {"image", "video"};
		String purposes[] = {"profile", "thumbnail", "arts", "community", "video"};
		
		// HashSet으로 중복 여부를 검사.
		HashSet<String> typeSet = new HashSet<>(Arrays.asList(types));
		HashSet<String> purposeSet = new HashSet<>(Arrays.asList(purposes));
		
		if(!typeSet.contains(type) || !purposeSet.contains(purpose))
		{
			throw new NotSupportUploadException();
		}
		
		if(type.equals(types[0]))
		{
			for (int i = 0; i < 4; i++) //for (int i = 0; i < purposes.length; i++)
			{
				if(purpose.equals(purposes[i]))
				{
					selectedResolution = i;
					break;
				}
			}
		}
	}
	
}
