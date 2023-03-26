package Baetube_backEnd.service.fcm;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FCMInitializer
{
	@PostConstruct
	public void initialize() throws IOException
	{
		FileInputStream serviceAccount = new FileInputStream("/Baetube_backEnd/src/main/resources/baetube-cfe4a-firebase-adminsdk-hlx9c-bbdeeb8834.json");

		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setProjectId("baetube-cfe4a")
				.build();

		FirebaseApp.initializeApp(options);
	}
}
