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
		
		// ��û���� noneImage�� ���ԵǾ� �ִٸ� �̹��� ������ ���ٴ� �ǹ��̹Ƿ� 
		// ��ȯ�ϴ� ���� �̹��� UUID�� ������ �ʿ䰡 ����.
		// ���� ImageUrl �Ӽ��� ���𰡰� ���ԵǾ� ���� ���� ��쿡�� uuid�� �������ָ� �ȴ�.
		if(request.getImageUrl() == null)
		{
			imageUUID = UUIDUtil.createUUID();
		}
		
		request.setImageUrl(imageUUID);
		
		System.out.println("comment : " + request.getComment());
		
		// Ŀ�´�Ƽ ����.
		communityMapper.insert(request);
		
		// ���� ���� ��ȯ�Ѵ�.
		HashMap<String, String> result = new HashMap<>();
				
		result.put(FCMSendService.FCM_NOTIFICATION_COMMUNITY, request.getCommunityId().toString());
		result.put("insertType", "community");
		result.put("imageUUID", imageUUID);
		result.put("communityId", String.valueOf(request.getCommunityId()));
		
		return result;
	}
}
