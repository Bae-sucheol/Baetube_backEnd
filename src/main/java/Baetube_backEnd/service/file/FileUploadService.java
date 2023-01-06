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
			
			if(mFile.getSize() != 0) // ���� üũ
			{
				if(!file.exists()) // ���� ���� ���� Ȯ��
				{
					if(file.getParentFile().mkdir()) // ���丮 ����
					{
						file.createNewFile(); // ���� ����
					}
				}
				
				mFile.transferTo(file); // �ӽ� ����� multipartFile�� ���� ���Ϸ� ����
				
			}
		}
		
		return true;
	}
}
