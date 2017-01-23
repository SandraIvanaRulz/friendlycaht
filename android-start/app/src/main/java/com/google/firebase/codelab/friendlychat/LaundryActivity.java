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


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class LaundryActivity extends AppCompatActivity implements
        View.OnClickListener {

        Button btnDatePicker, btnTimePicker;
        EditText txtDate, txtTime;
private int mYear, mMonth, mDay, mHour, mMinute;
        private Button mSendTaxiButton;
        private DatabaseReference mFirebaseDatabaseReference;
        public static final String MESSAGES_CHILD = "messages";
        private String mUsername;
        private String mPhotoUrl;
        private FirebaseAuth mFirebaseAuth;
        private static final String TAG = "LaundryActivity";
        private TextView notificationText;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_taxi);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = mFirebaseAuth.getCurrentUser().getDisplayName();
        mPhotoUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();


        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        mSendTaxiButton = (Button) findViewById(R.id.sendTaxiButton);

        mSendTaxiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        // Send messages on click.
                        String theMsg = "LAUNDRY ".concat(txtDate.getText().toString()).concat("  ").concat(txtTime.getText().toString());
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

@Override
public void onClick(View v) {

        if (v == btnDatePicker) {

// Get Current Date
final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
        new DatePickerDialog.OnDateSetListener() {

@Override
public void onDateSet(DatePicker view, int year,
                      int monthOfYear, int dayOfMonth) {

        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

        }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
        }
        if (v == btnTimePicker) {

// Get Current Time
final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
        new TimePickerDialog.OnTimeSetListener() {

@Override
public void onTimeSet(TimePicker view, int hourOfDay,
                      int minute) {

        txtTime.setText(hourOfDay + ":" + minute);
        }
        }, mHour, mMinute, false);
        timePickerDialog.show();
        }
        }
}
