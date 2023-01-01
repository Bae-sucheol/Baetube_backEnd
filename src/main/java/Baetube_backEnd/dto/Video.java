package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class Video
{
	private Integer videoId;
	private Long contentsId;
	private Integer channelId;
	private String url;
	private Integer visible;
	private String thumbnail;
	private String title;
	private String info;
	private String location;
	private Integer age;
	private Integer views;
	private Integer like;
	private Integer hate;
	private Integer replyCount;
	private Timestamp date;
	private Integer categoryId;
	private String profile;
	private Timestamp historyDate;
	private Integer playlistItemId;
	private Integer playlistId;
	
	// constructor
	public Video(Integer videoId, Long contentsId, Integer channelId, String url, Integer visible, String thumbnail, String title,
			String info, String location, Integer age, Integer views, Integer like, Integer hate, Integer replyCount,
			Timestamp date, Integer categoryId, String profile)
	{
		super();
		this.videoId = videoId;
		this.contentsId = contentsId;
		this.channelId = channelId;
		this.url = url;
		this.visible = visible;
		this.thumbnail = thumbnail;
		this.title = title;
		this.info = info;
		this.location = location;
		this.age = age;
		this.views = views;
		this.like = like;
		this.hate = hate;
		this.replyCount = replyCount;
		this.date = date;
		this.categoryId = categoryId;
		this.profile = profile;
		
	}

	public Video(Integer videoId, Long contentsId, Integer channelId, String url, Integer visible, String thumbnail,
			String title, String info, String location, Integer age, Integer views, Integer like, Integer hate,
			Integer replyCount, Timestamp date, Integer categoryId, String profile, Integer playlistItemId,
			Integer playlistId)
	{
		super();
		this.videoId = videoId;
		this.contentsId = contentsId;
		this.channelId = channelId;
		this.url = url;
		this.visible = visible;
		this.thumbnail = thumbnail;
		this.title = title;
		this.info = info;
		this.location = location;
		this.age = age;
		this.views = views;
		this.like = like;
		this.hate = hate;
		this.replyCount = replyCount;
		this.date = date;
		this.categoryId = categoryId;
		this.profile = profile;
		this.playlistItemId = playlistItemId;
		this.playlistId = playlistId;
	}

	public Video(Integer videoId, Long contentsId, Integer channelId, String url, Integer visible, String thumbnail,
			String title, String info, String location, Integer age, Integer views, Integer like, Integer hate,
			Integer replyCount, Timestamp date, Integer categoryId, String profile, Timestamp historyDate,
			Integer playlistItemId, Integer playlistId)
	{
		super();
		this.videoId = videoId;
		this.contentsId = contentsId;
		this.channelId = channelId;
		this.url = url;
		this.visible = visible;
		this.thumbnail = thumbnail;
		this.title = title;
		this.info = info;
		this.location = location;
		this.age = age;
		this.views = views;
		this.like = like;
		this.hate = hate;
		this.replyCount = replyCount;
		this.date = date;
		this.categoryId = categoryId;
		this.profile = profile;
		this.historyDate = historyDate;
		this.playlistItemId = playlistItemId;
		this.playlistId = playlistId;
	}

	public Video(Integer channelId, String url, Integer visible, String thumbnail, String title, String info,
			String location, Integer age, Integer views, Integer like, Integer hate, Integer replyCount, Timestamp date,
			Integer categoryId, String profile)
	{
		super();
		this.channelId = channelId;
		this.url = url;
		this.visible = visible;
		this.thumbnail = thumbnail;
		this.title = title;
		this.info = info;
		this.location = location;
		this.age = age;
		this.views = views;
		this.like = like;
		this.hate = hate;
		this.replyCount = replyCount;
		this.date = date;
		this.categoryId = categoryId;
		this.profile = profile;
	}

	public Video(Integer videoId, Long contentsId, Integer channelId, String url, Integer visible, String thumbnail, String title,
			String info, String location, Integer age, Integer views, Integer like, Integer hate, Integer replyCount,
			Timestamp date, Integer categoryId, String profile, Timestamp historyDate)
	{
		super();
		this.videoId = videoId;
		this.contentsId = contentsId;
		this.channelId = channelId;
		this.url = url;
		this.visible = visible;
		this.thumbnail = thumbnail;
		this.title = title;
		this.info = info;
		this.location = location;
		this.age = age;
		this.views = views;
		this.like = like;
		this.hate = hate;
		this.replyCount = replyCount;
		this.date = date;
		this.categoryId = categoryId;
		this.profile = profile;
		this.historyDate = historyDate;
	}
	
	// getter
	public Integer getVideoId()
	{
		return videoId;
	}

	public Long getContentsId()
	{
		return contentsId;
	}

	public Integer getChannelId()
	{
		return channelId;
	}

	public String getUrl()
	{
		return url;
	}

	public Integer getVisible()
	{
		return visible;
	}

	public String getThumbnail()
	{
		return thumbnail;
	}

	public String getTitle()
	{
		return title;
	}

	public String getInfo()
	{
		return info;
	}

	public String getLocation()
	{
		return location;
	}

	public Integer getAge()
	{
		return age;
	}

	public Integer getViews()
	{
		return views;
	}

	public Integer getLike()
	{
		return like;
	}

	public Integer getHate()
	{
		return hate;
	}

	public Integer getReplyCount()
	{
		return replyCount;
	}

	public Timestamp getDate()
	{
		return date;
	}

	public Integer getCategoryId()
	{
		return categoryId;
	}

	public String getProfile()
	{
		return profile;
	}

	public Timestamp getHistoryDate()
	{
		return historyDate;
	}

	public Integer getPlaylistItemId()
	{
		return playlistItemId;
	}

	public Integer getPlaylistId()
	{
		return playlistId;
	}
	
	// setter
	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}

	public void setContentsId(Long contentsId)
	{
		this.contentsId = contentsId;
	}

	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setVisible(Integer visible)
	{
		this.visible = visible;
	}

	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public void setViews(Integer views)
	{
		this.views = views;
	}

	public void setLike(Integer like)
	{
		this.like = like;
	}

	public void setHate(Integer hate)
	{
		this.hate = hate;
	}

	public void setReplyCount(Integer replyCount)
	{
		this.replyCount = replyCount;
	}

	public void setDate(Timestamp date)
	{
		this.date = date;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public void setProfile(String profile)
	{
		this.profile = profile;
	}

	public void setHistoryDate(Timestamp historyDate)
	{
		this.historyDate = historyDate;
	}

	public void setPlaylistItemId(Integer playlistItemId)
	{
		this.playlistItemId = playlistItemId;
	}

	public void setPlaylistId(Integer playlistId)
	{
		this.playlistId = playlistId;
	}

}
