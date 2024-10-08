package com.demo.forumapp.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FireBaseConfig {
	
	@PostConstruct
	public void initializeApp() throws IOException {
			
			FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
			FirebaseOptions options =  FirebaseOptions.builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

			if (FirebaseApp.getApps().isEmpty()) {
		            FirebaseApp.initializeApp(options);
		        }
			
		
	}
}
