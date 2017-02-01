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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransferActivity extends AppCompatActivity implements
        View.OnClickListener,AdapterView.OnItemSelectedListener {

        Button btnDatePicker, btnTimePicker;
        EditText txtDate, txtTime;
        private int mYear, mMonth, mDay, mHour, mMinute;
        private Button mSendTransferButton;
        private DatabaseReference mFirebaseDatabaseReference;
        public static final String MESSAGES_CHILD = "messages";
        private String mUsername;
        private String mPhotoUrl;
        private FirebaseAuth mFirebaseAuth;
        private static final String TAG = "TransferActivity";
        private TextView notificationText;
        Spinner country,city;
        private RadioGroup radioGroup;
        private RadioButton radioButton;


        @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_transfer);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = mFirebaseAuth.getCurrentUser().getDisplayName();
        mPhotoUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
            onRadioButtonClicked((Button)findViewById(R.id.radio_car));
            onRadioButtonClicked((Button)findViewById(R.id.radio_van));

            country = (Spinner)findViewById(R.id.spinner_country);
            city = (Spinner)findViewById(R.id.spinner_city);

            country.setOnItemSelectedListener(this);


        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        mSendTransferButton = (Button) findViewById(R.id.sendTransferButton);

        mSendTransferButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);

                        // Send messages on click.
                        String theMsg = "Transfer ".concat(txtDate.getText().toString()).concat("  ").
                                concat(txtTime.getText().toString()).
                                concat("Vehicle: ").concat(radioButton.getText().toString()).concat("  ").
                                concat("Destination: ").concat(country.getSelectedItem().toString()).concat("  ").
                                concat("  ").concat(city.getSelectedItem().toString());
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_car:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_van:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1= String.valueOf(country.getSelectedItem());
        String sp2= String.valueOf(city.getSelectedItem());
        Toast.makeText(this, sp2, Toast.LENGTH_SHORT).show();
        if(sp1.contentEquals("Macedonia")) {
            List<String> list = new ArrayList<String>();
            list.add("Berovo 160km 4300den");
            list.add("Bitola 169km 4600den");
            list.add("Valandovo 137km 3700den");
            list.add("Veles 48km 1500den");
            list.add("Vinica 120km 3200den");
            list.add("Geavgelija 158km 4300den");
            list.add("Gostivar 67km 1800den");
            list.add("Debar 131km 3500den");
            list.add("Delchevo 168km 4500den");
            list.add("Demir Kapija 100km 2700den");
            list.add("Dojran 177km 4800den");
            list.add("Katlanovska Banja 27km 900den");
            list.add("Kavadarci 105km 2800den");
            list.add("Kicevo 112km 3000den");
            list.add("Kocani 115km 3100den");
            list.add("Kratovo 93km 2500den");
            list.add("Kriva Palanka 100km 2700den");
            list.add("Krushevo 159km 4300den");
            list.add("Kumanovo 40km 1300den");
            list.add("Mavrovo 100km 2700den");
            list.add("Negotino 94km 2500den");
            list.add("Ohrid 174km 4700den");
            list.add("Pretor 198km 5300den");
            list.add("Prilep 128km 3500den");
            list.add("Probishtip 110km 3000den");
            list.add("Radovish 125km 3400den");
            list.add("Resen 198km 5300den");
            list.add("Sveti Nikole 80km 2200den");
            list.add("Struga 171km 4600den");
            list.add("Strumica 149km 4000den");
            list.add("Tetovo 42km 1300den");
            list.add("Shtip 86km 2300den");
            list.add("Rafinerija 900den");
            list.add("Aleksandar Veliki 900den");
            list.add("Blace 700den");
            list.add("Jonsons Control 900den");
            list.add("Deve Bair 3500den");
            list.add("Medzitlija 180km 4600den");
            list.add("Tabanovce 1500den");
            list.add("Kafasan 187km 5100den");
            list.add("Bogorodica 4300den");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            city.setAdapter(dataAdapter);
        }
        if(sp1.contentEquals("Albania")) {
            List<String> list = new ArrayList<String>();
            list.add("Tirana 200€");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }
        if(sp1.contentEquals("Greece")) {
            List<String> list = new ArrayList<String>();
            list.add("Thessaloniki 149€");
            list.add("Atina 350€");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }
        if(sp1.contentEquals("Serbia")) {
            List<String> list = new ArrayList<String>();
            list.add("Belgrade 250€");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }
        if(sp1.contentEquals("Bosnia")) {
            List<String> list = new ArrayList<String>();
            list.add("Sarajevo 300€");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Bulgaria")) {
            List<String> list = new ArrayList<String>();
            list.add("Sofija 140€");
             ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }
        if(sp1.contentEquals("Kosovo")) {
            List<String> list = new ArrayList<String>();
            list.add("Prishtina 80€");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            city.setAdapter(dataAdapter2);
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
