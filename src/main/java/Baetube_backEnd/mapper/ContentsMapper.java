package Baetube_backEnd.mapper;

public interface ContentsMapper
{
	public void delete(Long contentsID, Integer type);
	public void updateLike(Long contentsId, Integer value);
	public void updateHate(Long contentsId, Integer value);
	public void updateReplyCount(Long contentsId, Integer value);
}
