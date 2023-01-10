package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

public interface CategoryMapper
{
	public void insert(@Param("name") String name);
	public void delete(@Param("categoryId") Integer categoryId);
}
