package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class NestedReply
{
	private Integer nestedReplyId;
	private Integer replyId;
	private Long contentsId;
	private Integer channelId;
	private String comment;
	private Timestamp date;
	private String name;
	private String profile;
	
	// constructor
	public NestedReply(Integer nestedReplyId, Integer replyId, Long contentsId, Integer channelId, String comment,
			Timestamp date, String name, String profile)
	{
		super();
		this.nestedReplyId = nestedReplyId;
		this.replyId = replyId;
		this.contentsId = contentsId;
		this.channelId = channelId;
		this.comment = comment;
		this.date = date;
		this.name = name;
		this.profile = profile;
	}

	public NestedReply(Integer replyId, Integer channelId, String comment, Timestamp date)
	{
		super();
		this.replyId = replyId;
		this.channelId = channelId;
		this.comment = comment;
		this.date = date;
	}
	
	// getter
	public Integer getNestedReplyId()
	{
		return nestedReplyId;
	}

	public Integer getReplyId()
	{
		return replyId;
	}

	public Long getContentsId()
	{
		return contentsId;
	}

	public Integer getChannelId()
	{
		return channelId;
	}

	public String getComment()
	{
		return comment;
	}

	public Timestamp getDate()
	{
		return date;
	}

	public String getName()
	{
		return name;
	}

	public String getProfile()
	{
		return profile;
	}
	
	
	// setter
	public void setNestedReplyId(Integer nestedReplyId)
	{
		this.nestedReplyId = nestedReplyId;
	}

	public void setReplyId(Integer replyId)
	{
		this.replyId = replyId;
	}

	public void setContentsId(Long contentsId)
	{
		this.contentsId = contentsId;
	}

	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public void setDate(Timestamp date)
	{
		this.date = date;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setProfile(String profile)
	{
		this.profile = profile;
	}
	
	
	
	
	
}
