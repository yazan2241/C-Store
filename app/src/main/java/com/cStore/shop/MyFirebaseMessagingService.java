package com.cStore.shop;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token){
       Log.d("tag","the taken reffered"+token);
        System.out.println("this is "+token);
    }

/*

    FileInputStream serviceAccount =
            new FileInputStream("path/to/serviceAccountKey.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://cstore-62b96.firebaseio.com")
            .build();

FirebaseApp.initializeApp(options);

    Generate new private key
*/
}
