package Baetube_backEnd;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.messaging.FirebaseMessagingException;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.service.contents.ContentsDeleteService;
import Baetube_backEnd.service.fcm.FCMSendService;

public class FCMTEST
{
	@Autowired
	private FCMSendService fcmSendService = new FCMSendService();
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest() throws FirebaseMessagingException
	{
		List<String> registrationTokens = new ArrayList<>();
		String token = "euxxD-WvQrm7fQt3r59b-O:APA91bEhxliPvDVB0oBSoresa-sDg7MySHRLLbZcjixeRU3OKJivhADnv29HgaqEcD_yJanPZiqcSAdqx5wQ297UmO8x6i0C3EnNgDlpJoQRNDjyS2BDRsE7JZMk9KBJyLjqqpNHM7XI";
		String bodyMessage = "testMessage";
		
		registrationTokens.add(token);
		
		fcmSendService.sendMultiMessage(registrationTokens, bodyMessage, FCMSendService.FCM_NOTIFICATION_VIDEO, "67");
	}
}
