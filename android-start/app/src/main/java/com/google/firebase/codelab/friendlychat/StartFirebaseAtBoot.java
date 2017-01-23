package com.google.firebase.codelab.friendlychat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ivana on 11/3/2016.
 */
public class StartFirebaseAtBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(FirebaseBackgroundService.class.getName()));
    }
}
