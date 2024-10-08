package com.demo.forumapp.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.demo.forumapp.models.UserModel;
import com.google.api.core.ApiFuture;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

	public List<UserModel> getALlUsers() throws InterruptedException, ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		return dbFirestore.collection("users")
				.get()
				.get()
				.toObjects(UserModel.class);
				
	}
	
	public String createUser(UserModel user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		user.setCreatedAt(Timestamp.now());
		user.setUpdatedAt(Timestamp.now());
		CollectionReference collection = dbFirestore.collection("users");
		
		ApiFuture<DocumentReference> addDocument = collection.add(user);
		String generateId = addDocument.get().getId();
		return "User has been added with ID " + generateId;
	}
	
	public UserModel getUserDetails(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference users = dbFirestore.collection("users");
		
		Query query = users.whereEqualTo("name", name);
		ApiFuture<QuerySnapshot> querySnapshot =  query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		
		
		
		if(!documents.isEmpty()) {
			QueryDocumentSnapshot document = documents.get(0); 
			UserModel user = document.toObject(UserModel.class);
			return user;
		}else {
			return null;
		}
		
	}
	
	public String updateUser(UserModel user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		user.setUpdatedAt(Timestamp.now());
		ApiFuture<WriteResult> future = dbFirestore.collection("users").document(user.getName()).set(user);
		return future.get().getUpdateTime().toString();
	}
	
	public String deleteUser(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference users = dbFirestore.collection("users");
		Query query = users.whereEqualTo("name", name);
		
		ApiFuture<QuerySnapshot> querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		
		
		
		if(!documents.isEmpty()) {
			QueryDocumentSnapshot document = documents.get(0); 
			ApiFuture<WriteResult> writeResult = document.getReference().delete();
			return "The user " + name + " has been deleted";
		}else {
			 return "No user found with name '" + name + "'.";		}
		
	}

}
