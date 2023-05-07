package Baetube_backEnd.service.category;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Category;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullCategoryException;
import Baetube_backEnd.mapper.CategoryMapper;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;

public class CategorySelectServiceTest
{
	@InjectMocks
	private CategorySelectService categorySelectService;
	@Mock
	private CategoryMapper categoryMapper;
	@Mock
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	private String bearerToken;
	private User user;
	private List<Category> categoryList;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		bearerToken = "test";
		user = new User();
		user.setUserId(1);
		categoryList = new ArrayList<Category>();
		categoryList.add(new Category());
	}
	
	@Test
	public void correctTest()
	{
		when(jwtTokenDataExtractService.getUserData(bearerToken)).thenReturn(user);
		when(categoryMapper.selectOrderByViews(user.getUserId())).thenReturn(categoryList);
		
		assertEquals(categoryList, categorySelectService.selectCategoryOrderByViews(bearerToken));
	}
	
	@Test(expected = NullCategoryException.class)
	public void wrongTest()
	{
		when(jwtTokenDataExtractService.getUserData(bearerToken)).thenReturn(user);
		when(categoryMapper.selectOrderByViews(user.getUserId())).thenReturn(null);
		
		assertEquals(categoryList, categorySelectService.selectCategoryOrderByViews(bearerToken));
	}
}
