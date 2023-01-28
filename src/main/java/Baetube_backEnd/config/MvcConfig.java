package Baetube_backEnd.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer
{
	
	private static final String CLASSPATH_RESOURCE_LOCATIONS[] = {
			"classpath:/static/",
			"classpath:/public/",
			"classpath:/",
			"classpath:/resources/",
			"classpath:/META-INF/resources/",
			"classpath:/META-INF/resources/webjars"
	};
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		// TODO Auto-generated method stub
		configurer.enable();
		WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)
	{
		// TODO Auto-generated method stub
		registry.jsp("/WEB-INF/view/", ".jsp");
		WebMvcConfigurer.super.configureViewResolvers(registry);
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss");
		
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter))
				.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.build();
		
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addViewControllers(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
}