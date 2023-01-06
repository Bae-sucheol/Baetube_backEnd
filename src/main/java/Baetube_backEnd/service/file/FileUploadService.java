package Baetube_backEnd.service.file;

import java.io.File;
import java.util.Iterator;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadService
{
	private static String BASE_FILE_PATH = "G:\\baetube";
	
	public boolean upload(MultipartHttpServletRequest request) throws Exception
	{
		Iterator<String> fileNames = request.getFileNames();
		
		while(fileNames.hasNext())
		{
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
			File file = new File(BASE_FILE_PATH + "test");
			
			if(mFile.getSize() != 0) // 파일 체크
			{
				if(!file.exists()) // 파일 존재 여부 확인
				{
					if(file.getParentFile().mkdir()) // 디렉토리 생성
					{
						file.createNewFile(); // 파일 생성
					}
				}
				
				mFile.transferTo(file); // 임시 저장된 multipartFile을 실제 파일로 전송
				
			}
		}
		
		return true;
	}
}
