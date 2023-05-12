package Baetube_backEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.messaging.FirebaseMessagingException;

import Baetube_backEnd.service.fcm.FCMInitializer;
import Baetube_backEnd.service.fcm.FCMSendService;

public class FCMSendTest
{
	@Autowired
	private FCMSendService fcmSendService;
	@Autowired
	private FCMInitializer fcmInitializer;

	
	@Before
	public void setUp() throws IOException
	{
		fcmInitializer = new FCMInitializer();
		fcmInitializer.initialize();
		fcmSendService = new FCMSendService();
	}
	
	@Test
	public void test() throws FirebaseMessagingException
	{
		List<String> registrationTokens = new ArrayList<>();
		registrationTokens.add("cXFDgmbNSIu3T9BBmygP5O:APA91bFYDMzQwHIVG_8TPvaG-Q36frmx8631RjVeZs_ZOWRa36RdgmijiVTf_Lx7VvAvmrWLNv3H5omk-VZjsZG_py7-Q90BqnHEgnXTuRPqd-qmqsIjpz5fHv6lagbihYH8Gl886uxW");
		String bodyMessage = "홍길동에서 새로운 동영상을 업로드 했습니다.";
		String notificationType = FCMSendService.FCM_NOTIFICATION_VIDEO;
		String data = "1";
		
		fcmSendService.sendMessage("cXFDgmbNSIu3T9BBmygP5O:APA91bFYDMzQwHIVG_8TPvaG-Q36frmx8631RjVeZs_ZOWRa36RdgmijiVTf_Lx7VvAvmrWLNv3H5omk-VZjsZG_py7-Q90BqnHEgnXTuRPqd-qmqsIjpz5fHv6lagbihYH8Gl886uxW", bodyMessage, notificationType, data, notificationType, data);
	}
}
