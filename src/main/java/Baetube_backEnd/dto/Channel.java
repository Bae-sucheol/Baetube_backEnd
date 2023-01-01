package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class Channel
{
	private Integer ChannelID;
	private Integer userId;
	private Integer subs;
	private Integer videoCount;
	private String name;
	private String description;
	private String arts;
	private Timestamp regDate;
	
	
	
	public Channel(Integer channelID, Integer userId, Integer subs, Integer videoCount, String name, String description,
			String arts, Timestamp regDate)
	{
		super();
		ChannelID = channelID;
		this.userId = userId;
		this.subs = subs;
		this.videoCount = videoCount;
		this.name = name;
		this.description = description;
		this.arts = arts;
		this.regDate = regDate;
	}

	public Channel(Integer userId, Integer subs, Integer videoCount, String name, String description, String arts,
			Timestamp regDate)
	{
		super();
		this.userId = userId;
		this.subs = subs;
		this.videoCount = videoCount;
		this.name = name;
		this.description = description;
		this.arts = arts;
		this.regDate = regDate;
	}
	
	// 초기 insert 전용.
	public Channel(Integer userId, String name, Timestamp regDate)
	{
		super();
		this.userId = userId;
		this.name = name;
		this.regDate = regDate;
	}



	// setter
	public Integer getChannelID()
	{
		return ChannelID;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public Integer getSubs()
	{
		return subs;
	}
	public Integer getVideoCount()
	{
		return videoCount;
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public String getArts()
	{
		return arts;
	}
	public Timestamp getRegDate()
	{
		return regDate;
	}
	
	// getter
	public void setChannelID(Integer channelID)
	{
		ChannelID = channelID;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public void setSubs(Integer subs)
	{
		this.subs = subs;
	}
	public void setVideoCount(Integer videoCount)
	{
		this.videoCount = videoCount;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public void setArts(String arts)
	{
		this.arts = arts;
	}
	public void setRegDate(Timestamp regDate)
	{
		this.regDate = regDate;
	}
	
	
	
}
