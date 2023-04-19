package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Category;

public interface CategoryMapper
{
	public void insert(@Param("name") String name);
	public void delete(@Param("categoryId") Integer categoryId);
	public List<Category> selectOrderByViews(@Param("userId") Integer userId);
}
