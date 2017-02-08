/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.firebase.codelab.friendlychat;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PillowActivity  extends AppCompatActivity {
    private Button mSendPillowButton;
    private DatabaseReference mFirebaseDatabaseReference;
    public static final String MESSAGES_CHILD = "messages";
    private String mUsername;
    private String mPhotoUrl;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "PillowActivity";
    private TextView notificationText;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_pillow);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = mFirebaseAuth.getCurrentUser().getDisplayName();
        mPhotoUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        onRadioButtonClicked((Button)findViewById(R.id.radio_regular_pillow));
        onRadioButtonClicked((Button)findViewById(R.id.radio_natural_pillow));
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        mSendPillowButton = (Button) findViewById(R.id.sendPillowButton);

        mSendPillowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                // Send messages on click.
                String theMsg = "Pillow ".concat("  ").
                        concat(radioButton.getText().toString());
                FriendlyMessage friendlyMessage = new
                        FriendlyMessage(theMsg,
                        mUsername,
                        mPhotoUrl);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD)
                        .push().setValue(friendlyMessage);


                notificationText = (TextView) findViewById(R.id.notificationText);
                notificationText.setVisibility(View.VISIBLE);
                Log.d(TAG, "***** MSG SEND *****");

            }
        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_regular_pillow:
                if (checked)

                    break;
            case R.id.radio_natural_pillow:
                if (checked)
                    break;
        }
    }
}
