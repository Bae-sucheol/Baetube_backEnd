package Baetube_backEnd.service.fcm;

import java.util.List;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;

public class FCMSendService
{
	public static final String FCM_NOTIFICATION_VIDEO = "videoId";
	public static final String FCM_NOTIFICATION_COMMUNITY = "communityId";
	public static final String FCM_NOTIFICATION_REPLY = "replyId";
	public static final String FCM_NOTIFICATION_NESTED_REPLY = "nestedReplyId";
	
	public void sendMultiMessage(List<String> registrationTokens, String bodyMessage, String notificationType, String data) throws FirebaseMessagingException
	{
		
		MulticastMessage message = MulticastMessage.builder()
				.setAndroidConfig(AndroidConfig.builder()
						.setTtl(60 * 60 * 1000)
						.setPriority(AndroidConfig.Priority.HIGH)
						.setRestrictedPackageName("com.example.baetube")
						.setDirectBootOk(true)
						.setNotification(AndroidNotification.builder()
								.setTitle("BAETUBE")
								.setBody(bodyMessage)
								.build())
						.build())
				.putData(notificationType, data)
				.addAllTokens(registrationTokens)
				.build();
		
		BatchResponse batchResponse = FirebaseMessaging.getInstance().sendMulticast(message);
	}
	
	// 여기서 말하는 contentsId 는 video_id 또는 community_id를 의미한다.
	public void sendMessage(String registrationTokens, String bodyMessage, String notificationType, String data,
			String contentsType, String contentsId) throws FirebaseMessagingException
	{
		
		Message message = Message.builder()
				.setAndroidConfig(AndroidConfig.builder()
						.setTtl(60 * 60 * 1000)
						.setPriority(AndroidConfig.Priority.HIGH)
						.setRestrictedPackageName("com.example.baetube")
						.setDirectBootOk(true)
						.setNotification(AndroidNotification.builder()
								.setTitle("BAETUBE")
								.setBody(bodyMessage)
								.build())
						.build())
				.putData(notificationType, data)
				.putData(contentsType, contentsId)
				.setToken(registrationTokens)
				.build();
		
		String response = FirebaseMessaging.getInstance().send(message);
	}
	
}
