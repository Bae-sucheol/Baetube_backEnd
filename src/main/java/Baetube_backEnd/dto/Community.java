package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class Community
{
	 	private Integer communityId;
	 	private Long contentsId;
	    private Integer channelId;
	    private Integer likeCount;
	    private Integer hateCount;
	    private Integer replyCount;
	    private String imageUrl;
	    private String comment;
	    private Timestamp date;
	    
	    // constructor
	    
	    public Community()
		{
			super();
		}
	    
	    public Community(Integer communityId, Long contentsId, Integer channelId, Integer likeCount,
				Integer hateCount, Integer replyCount, String imageUrl, String comment, Timestamp date)
		{
			super();
			this.communityId = communityId;
			this.contentsId = contentsId;
			this.channelId = channelId;
			this.likeCount = likeCount;
			this.hateCount = hateCount;
			this.replyCount = replyCount;
			this.imageUrl = imageUrl;
			this.comment = comment;
			this.date = date;
		}

		public Community(Integer channelId, String imageUrl, String comment, Timestamp date)
		{
			super();
			this.channelId = channelId;
			this.imageUrl = imageUrl;
			this.comment = comment;
			this.date = date;
		}

		// getter
		public Integer getCommunityId()
		{
			return communityId;
		}
		public Long getContentsId()
		{
			return contentsId;
		}
		public Integer getChannelId()
		{
			return channelId;
		}
		public Integer getLikeCount()
		{
			return likeCount;
		}
		public Integer getHateCount()
		{
			return hateCount;
		}
		public Integer getReplyCount()
		{
			return replyCount;
		}
		public String getImageUrl()
		{
			return imageUrl;
		}
		public String getComment()
		{
			return comment;
		}
		public Timestamp getDate()
		{
			return date;
		}
		
		// setter
		public void setCommunityId(Integer communityId)
		{
			this.communityId = communityId;
		}
		public void setContentsId(Long contentsId)
		{
			this.contentsId = contentsId;
		}
		public void setChannelId(Integer channelId)
		{
			this.channelId = channelId;
		}
		public void setLikeCount(Integer likeCount)
		{
			this.likeCount = likeCount;
		}
		public void setHateCount(Integer hateCount)
		{
			this.hateCount = hateCount;
		}
		public void setReplyCount(Integer replyCount)
		{
			this.replyCount = replyCount;
		}
		public void setImageUrl(String imageUrl)
		{
			this.imageUrl = imageUrl;
		}
		public void setComment(String comment)
		{
			this.comment = comment;
		}
		public void setDate(Timestamp date)
		{
			this.date = date;
		}
	    
	    
}
