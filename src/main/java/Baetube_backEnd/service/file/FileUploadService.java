package Baetube_backEnd.service.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
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
	
	public boolean upload(String type, String purpose, String id, MultipartFile request) throws IOException, NotSupportUploadException
	{
		/* ��û�� Ÿ��, ������ �����Ѵ�.
		 * ������ �����ϸ� valid �޼ҵ忡�� NotSupportUploadException�� �߻���Ų��.
		 */
		valid(type, purpose);
		
		// ���̽� ���� path�� ������ Ÿ��(�̹���, ������) �� ������ ������ path�� baseFolderPath�� ����.
		String baseFolderPath = Paths.get(BASE_FILE_PATH, type).toString();
		
		// Ÿ���� video�� �ƴϸ� �� ������ �̹����� ������ �°�(������ ����, ä�ι��, �Խñ� �̹���) ������ �����ϱ� ���� path�� ����.
		if(!type.equals("video"))
		{
			baseFolderPath = Paths.get(baseFolderPath, purpose).toString();
		}
		
		/*
		 * ���� ������ ���ϸ��� �״�� ����ϴ� ���� ���� �ʴٰ� �Ǵ�.
		 * �ߺ����� �ʵ��� TimeStamp + Hash�� ����ϴ� ����� ������
		 * uuid�� ����ϱ�� ����.
		 */
		String uuid = UUID.randomUUID().toString();
		// Ȯ���ڸ� �����´�.
		String prefix = request.getOriginalFilename().substring(request.getOriginalFilename().lastIndexOf(".") + 1,
				request.getOriginalFilename().length());
		String fileName = uuid + "_" + id;
		String fileNamePrefix = fileName + "." + prefix;
		String destinationPath = Paths.get(baseFolderPath, fileNamePrefix).toString();
		
		File baseFolder = new File(baseFolderPath);
		File storageFile = new File(destinationPath);
		
		// baseFolde�� �������� ������ ������ ����.
		if(!baseFolder.exists())
		{
			baseFolder.mkdirs();
		}
		
		// multipart file request�� inputStream�� �޾ƿ´�.
		InputStream fileStream = request.getInputStream();
		
		// Ÿ���� �̹����� ������¡ �� ����.
		if(type.equals("image"))
		{
			saveImage(fileStream, storageFile);
		}
		else // ������ �������̸� ������ �����ϰ� FFmpeg�� ����Ͽ� hls ���ڵ�.
		{
			saveAndEncodingVideo(fileStream, storageFile, destinationPath, fileName);
		}
		
		return true;
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
			// inputStream�� ���Ϸ� ī���Ѵ�.
			FileUtils.copyInputStreamToFile(fileStream, storageFile);
			// ffmepg�� ����Ͽ� ��ȯ�ϴ� �۾��� ����.
			FFmpegWrapper wrapper = new FFmpegWrapper();
			wrapper.convert(destinationPath, fileName);
			// �۾��� ����Ǹ� �޾Ҵ� ������ ������ ����.
			FileUtils.deleteQuietly(storageFile);
		} 
		catch (IOException e)
		{
			// IOException�� �߻��ϸ� ������ ����� IOException�� ������.
			// ���� Exception�� Ŭ���� ������ ���� controller �ܿ��� ó���Ѵ�.
			FileUtils.delete(storageFile);
			e.printStackTrace();
		}
		
	}
	
	// ���� �̹����� ���ϴ� ������� ������¡.
	// ���� ���, ������, �Խñ۵� ������ �´� �̹��� �� ������ ��ȯ�� �����ؾ� �Ѵ�.
	private BufferedImage resize(InputStream fileStream) throws IOException
	{
		BufferedImage inputImage = ImageIO.read(fileStream);
		BufferedImage outputImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, inputImage.getType());
		
		Graphics2D graphics2d = outputImage.createGraphics();
		graphics2d.drawImage(inputImage, 0, 0, IMAGE_SIZE, IMAGE_SIZE, null);
		graphics2d.dispose();
		
		return outputImage;
	}
	
	// ��û�� Ÿ��, ������ �����ϴ� �޼ҵ�
	public void valid(String type, String purpose) throws NotSupportUploadException
	{
		// ������ Ÿ�԰� ������ �迭�� ����.
		String types[] = {"image", "video"};
		String purposes[] = {"profile", "thumbnail", "arts", "community", "video"};
		
		// HashSet���� �ߺ� ���θ� �˻�.
		HashSet<String> typeSet = new HashSet<>(Arrays.asList(types));
		HashSet<String> purposeSet = new HashSet<>(Arrays.asList(purposes));
		
		if(!typeSet.contains(type) || !purposeSet.contains(purpose))
		{
			throw new NotSupportUploadException();
		}
	}
}
