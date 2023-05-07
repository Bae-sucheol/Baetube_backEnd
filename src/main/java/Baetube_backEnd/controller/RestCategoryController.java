package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.dto.Category;
import Baetube_backEnd.exception.NullCategoryException;
import Baetube_backEnd.service.category.CategorySelectService;

@RestController
public class RestCategoryController
{
	@Autowired
	private CategorySelectService categorySelectService;
	
	@GetMapping("/api/category/select")
	public ResponseEntity<Object> selectCommunity(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{
		try
		{
			List<Category> categoryList = categorySelectService.selectCategoryOrderByViews(bearerToken);

			return ResponseEntity.status(HttpStatus.OK).body(categoryList);
		} 
		catch (NullCategoryException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
