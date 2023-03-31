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
	public void sendMultiMessage(List<String> registrationTokens, String bodyMessage) throws FirebaseMessagingException
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
				.addAllTokens(registrationTokens)
				.build();
		
		BatchResponse batchResponse = FirebaseMessaging.getInstance().sendMulticast(message);
	}
	
	
	public void sendMessage(String registrationTokens, String bodyMessage) throws FirebaseMessagingException
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
				.setToken(registrationTokens)
				.build();
		
		String response = FirebaseMessaging.getInstance().send(message);
	}
	
}
