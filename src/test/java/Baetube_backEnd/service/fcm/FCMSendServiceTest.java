package Baetube_backEnd.service.fcm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessagingException;

public class FCMSendServiceTest
{
	@Autowired
	private FCMSendService fcmSendService = new FCMSendService();
	
	@Before
	public void setUp() throws FileNotFoundException, IOException
	{
		ClassPathResource classPathResource = new ClassPathResource("baetube-cfe4a-firebase-adminsdk-hlx9c-bbdeeb8834.json");
		FileInputStream serviceAccount = new FileInputStream(classPathResource.getFile());

		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setProjectId("baetube-cfe4a")
				.build();

		FirebaseApp.initializeApp(options);
	}
	
	@Test
	public void correctTest()
	{
		
		String token = "euxxD-WvQrm7fQt3r59b-O:APA91bEhxliPvDVB0oBSoresa-sDg7MySHRLLbZcjixeRU3OKJivhADnv29HgaqEcD_yJanPZiqcSAdqx5wQ297UmO8x6i0C3EnNgDlpJoQRNDjyS2BDRsE7JZMk9KBJyLjqqpNHM7XI";
		
		try
		{
			fcmSendService.sendMessage(token);
		} 
		catch (FirebaseMessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
