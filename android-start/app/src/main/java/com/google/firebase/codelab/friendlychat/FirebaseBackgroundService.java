package com.google.firebase.codelab.friendlychat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ivana on 11/2/2016.
 */
public class FirebaseBackgroundService extends Service {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mFirebaseDatabaseReference;

//    private ValueEventListener handler;
    private ChildEventListener childEventListener;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this, FirebaseOptions.fromResource(this));
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                Log.i("friendly message json! ", dataSnapshot.getValue().toString() + "");
                Log.i("friendly message !!!!! ", friendlyMessage + "");
                Log.i("friendly name !!!!! ", friendlyMessage.getName() + "");
                Log.i("friendly text !!!!! ", friendlyMessage.getText() + "");
                Log.i("friendly photo !!!!! ", friendlyMessage.getPhotoUrl() + "");
                postNotif(friendlyMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //        handler = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        };


//        mFirebaseDatabaseReference.addValueEventListener(handler);

    }

    private void postNotif(FriendlyMessage friendlyMessage) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int icon = R.mipmap.ic_launcher;
       // Notification notification = new Notification(icon, "Firebase" + Math.random(), System.currentTimeMillis());

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(icon);
        mBuilder.setContentTitle(friendlyMessage.getName());
//        mBuilder.setContentText(friendlyMessage.getText().toString());

        mBuilder.setDefaults(Notification.DEFAULT_ALL);
//        mBuilder.setLights(0xFFff0000, 100, 100);

//		notification.flags |= Notification.FLAG_AUTO_CANCEL;

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        mBuilder.setContentIntent(contentIntent);

        mNotificationManager.notify(1, mBuilder.build());
    }

}
