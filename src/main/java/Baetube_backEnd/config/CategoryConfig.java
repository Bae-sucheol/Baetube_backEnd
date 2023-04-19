package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.category.CategorySelectService;

@Configuration
public class CategoryConfig
{
	@Bean
	public CategorySelectService categorySelectService()
	{
		return new CategorySelectService();
	}
}
