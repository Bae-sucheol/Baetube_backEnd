package Baetube_backEnd.dto;

public class Category
{
	private Integer categoryId;
	private String name;
	
	public Category()
	{
		super();
	}
	
	public Category(Integer categoryId, String name)
	{
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
	
	// getter
	public Integer getCategoryId()
	{
		return categoryId;
	}

	public String getName()
	{
		return name;
	}
	
	// setter
	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
