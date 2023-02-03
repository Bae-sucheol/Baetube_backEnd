package Baetube_backEnd.dto;

public class Notification
{
	private Integer userId;
	private Long contentsId;
	
	// constructor
	public Notification()
	{
		super();
	}
	
	public Notification(Integer userId, Long contentsId)
	{
		super();
		this.userId = userId;
		this.contentsId = contentsId;
	}
	
	// getter
	public Integer getUserId()
	{
		return userId;
	}
	public Long getContentsId()
	{
		return contentsId;
	}
	
	// setter
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public void setContentsId(Long contentsId)
	{
		this.contentsId = contentsId;
	}
	
	
}
