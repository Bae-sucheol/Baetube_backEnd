package Baetube_backEnd.dto;

public class Vote
{
	private Integer voteId;
	private Integer communityId;
	private String title;
	private Integer voteOptionId;
	private String option;
	private Integer count;
	
	// constructor
	public Vote()
	{
		super();
	}
	
	public Vote(Integer voteId, Integer communityId, String title, Integer voteOptionId, String option,
			Integer count)
	{
		super();
		this.voteId = voteId;
		this.communityId = communityId;
		this.title = title;
		this.voteOptionId = voteOptionId;
		this.option = option;
		this.count = count;
	}

	public Vote(Integer communityId, String title)
	{
		super();
		this.communityId = communityId;
		this.title = title;
	}
	
	public Vote(Integer voteId, Integer communityId, String title)
	{
		super();
		this.voteId = voteId;
		this.communityId = communityId;
		this.title = title;
	}
	
	public Vote(Integer voteId, Integer voteOptionId, String option, Integer count)
	{
		super();
		this.voteId = voteId;
		this.voteOptionId = voteOptionId;
		this.option = option;
		this.count = count;
	}

	// getter
	public Integer getVoteId()
	{
		return voteId;
	}
	public Integer getCommunityId()
	{
		return communityId;
	}
	public String getTitle()
	{
		return title;
	}
	public Integer getVoteOptionId()
	{
		return voteOptionId;
	}
	public String getOption()
	{
		return option;
	}
	public Integer getCount()
	{
		return count;
	}
	
	// setter
	public void setVoteId(Integer voteId)
	{
		this.voteId = voteId;
	}
	public void setCommunityId(Integer communityId)
	{
		this.communityId = communityId;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setVoteOptionId(Integer voteOptionId)
	{
		this.voteOptionId = voteOptionId;
	}
	public void setOption(String option)
	{
		this.option = option;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	
	
}
