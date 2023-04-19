package Baetube_backEnd.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Category;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullCategoryException;
import Baetube_backEnd.mapper.CategoryMapper;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;

public class CategorySelectService
{
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	public List<Category> selectCategoryOrderByViews(String bearerToken) throws NullCategoryException
	{
		User user = jwtTokenDataExtractService.getUserData(bearerToken);
		
		List<Category> categoryList = categoryMapper.selectOrderByViews(user.getUserId());
		
		if(categoryList == null || categoryList.isEmpty())
		{
			throw new NullCategoryException();
		}
		
		return categoryList;
	}
}
