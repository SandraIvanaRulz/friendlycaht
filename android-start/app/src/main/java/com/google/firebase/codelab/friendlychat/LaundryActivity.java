/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.firebase.codelab.friendlychat;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LaundryActivity extends Activity {

    MyCustomAdapter dataAdapter = null;
    private Button mSendLaundryButton;
    private DatabaseReference mFirebaseDatabaseReference;
    public static final String MESSAGES_CHILD = "messages";
    private String mUsername;
    private String mPhotoUrl;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "LaundryActivity";
    private TextView notificationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_laundry_main);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = mFirebaseAuth.getCurrentUser().getDisplayName();
        mPhotoUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();

        //Generate list View from ArrayList
        displayListView();

        ArrayList<String> selectedStrings = new ArrayList<String>();

        mSendLaundryButton = (Button) findViewById(R.id.sendLaundryButton);

        mSendLaundryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<LaundryItem> countryList = dataAdapter.countryList;
                for (int i = 0; i < countryList.size(); i++) {
                    LaundryItem country = countryList.get(i);
                    if (country.isSelected()) {
                        responseText.append("\n" + country.getName());
                    }
                }


                // Send messages on click.
                String theMsg = "Laundry ".concat(responseText.toString()).concat("  ");
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


    private void displayListView() {

        //Array list of countries
        ArrayList<LaundryItem> countryList = new ArrayList<LaundryItem>();
        LaundryItem country = new LaundryItem("9€", "Suit", false);
        countryList.add(country);
        country = new LaundryItem("4.5€", "Pants", false);
        countryList.add(country);
        country = new LaundryItem("3€", "Short Pants", false);
        countryList.add(country);
        country = new LaundryItem("11€", "Long Coat", false);
        countryList.add(country);
        country = new LaundryItem("9€", "Short Coat", false);
        countryList.add(country);
        country = new LaundryItem("5.5€", "Child Coat", false);
        countryList.add(country);
        country = new LaundryItem("7€", "Sport Jacket", false);
        countryList.add(country);
        country = new LaundryItem("11€", "Feather Jacket", false);
        countryList.add(country);
        country = new LaundryItem("8€", "Child Feather Jacket", false);
        countryList.add(country);
        country = new LaundryItem("4€", "Short Skirt", false);
        countryList.add(country);
        country = new LaundryItem("4.5€", "Long Skirt", false);
        countryList.add(country);
        country = new LaundryItem("7.5€", "Pleated Skirt", false);
        countryList.add(country);
        country = new LaundryItem("6.5€", "Dress", false);
        countryList.add(country);
        country = new LaundryItem("25€", "Wedding Dress", false);
        countryList.add(country);
        country = new LaundryItem("15€", "Child Wedding Dress", false);
        countryList.add(country);
        country = new LaundryItem("5€", "Sweater", false);
        countryList.add(country);
        country = new LaundryItem("2.5€", "Shirt", false);
        countryList.add(country);
        country = new LaundryItem("1.5€", "Vest", false);
        countryList.add(country);
        country = new LaundryItem("4€", "Jeans", false);
        countryList.add(country);
        country = new LaundryItem("6.5€", "Hand Bag", false);
        countryList.add(country);
        country = new LaundryItem("3.5€", "Blouse", false);
        countryList.add(country);
        country = new LaundryItem("1.5€", "T-Shirt", false);
        countryList.add(country);
        country = new LaundryItem("1.9€", "Polo", false);
        countryList.add(country);
        country = new LaundryItem("1€", "Underwear", false);
        countryList.add(country);
        country = new LaundryItem("0.5€", "Socks", false);
        countryList.add(country);
        country = new LaundryItem("1€", "Towel", false);
        countryList.add(country);
        country = new LaundryItem("7€", "Path Robe", false);
        countryList.add(country);
        country = new LaundryItem("0.5€", "Small Clout", false);
        countryList.add(country);


        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.guest_laundry, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                LaundryItem country = (LaundryItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + country.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<LaundryItem> {

        private ArrayList<LaundryItem> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<LaundryItem> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<LaundryItem>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.guest_laundry, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        LaundryItem country = (LaundryItem) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        country.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            LaundryItem country = countryList.get(position);
            holder.code.setText(" (" + country.getPrice() + ")");
            holder.name.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);

            return convertView;

        }

    }


}
