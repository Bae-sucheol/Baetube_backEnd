package Baetube_backEnd.dto;

public class PlaylistItem
{
	private Integer playlistId;
	private Integer videoId;
	
	public PlaylistItem()
	{
		super();
	}

	public PlaylistItem(Integer playlistId, Integer videoId)
	{
		super();
		this.playlistId = playlistId;
		this.videoId = videoId;
	}

	// getter
	public Integer getPlaylistId()
	{
		return playlistId;
	}
	public Integer getVideoId()
	{
		return videoId;
	}
	
	// setter
	public void setPlaylistId(Integer playlistId)
	{
		this.playlistId = playlistId;
	}
	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}
	
	
}
