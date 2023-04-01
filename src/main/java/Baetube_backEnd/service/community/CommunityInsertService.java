package Baetube_backEnd.service.community;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.mapper.CommunityMapper;
import Baetube_backEnd.service.fcm.FCMSendService;

public class CommunityInsertService
{
	@Autowired
	private CommunityMapper communityMapper;
	
	public HashMap<String, String> insertCommunity(Community request)
	{
		String imageUUID = null;
		
		// 요청으로 noneImage가 삽입되어 있다면 이미지 삽입이 없다는 의미이므로 
		// 반환하는 값에 이미지 UUID를 삽입할 필요가 없다.
		// 따라서 ImageUrl 속성에 무언가가 삽입되어 있지 않은 경우에만 uuid를 생성해주면 된다.
		if(request.getImageUrl() == null)
		{
			imageUUID = UUIDUtil.createUUID();
		}
		
		request.setImageUrl(imageUUID);
		
		System.out.println("comment : " + request.getComment());
		
		// 커뮤니티 삽입.
		communityMapper.insert(request);
		
		// 맵을 만들어서 반환한다.
		HashMap<String, String> result = new HashMap<>();
				
		result.put(FCMSendService.FCM_NOTIFICATION_COMMUNITY, request.getCommunityId().toString());
		result.put("insertType", "community");
		result.put("imageUUID", imageUUID);
		result.put("communityId", String.valueOf(request.getCommunityId()));
		
		return result;
	}
}
