package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.file.FileUploadService;

@Configuration
public class FileConfig
{
	@Bean
	public FileUploadService fileUploadService()
	{
		return new FileUploadService();
	}

}
