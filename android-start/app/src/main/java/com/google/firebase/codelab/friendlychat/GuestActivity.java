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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GuestActivity extends AppCompatActivity implements
         View.OnClickListener {

    private static final String TAG = "GuestActivity";
    private static final int RC_SIGN_IN = 9001;

    private Button mTaxiButton;
    private Button mWakeUpCallButton;
    private Button mTransferButton;
    private Button mDryCleaningButton;
    private Button mRentABikeButton;
    private Button mRentACarButton;
    private Button mFoodButton;
    private Button mBusiness;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_main);

        // Assign fields
        mTaxiButton = (Button) findViewById(R.id.taxi);
        mWakeUpCallButton = (Button) findViewById(R.id.wakeupcall);
        mTransferButton = (Button) findViewById(R.id.transfer);
        mDryCleaningButton = (Button) findViewById(R.id.drycleaning);
        mRentABikeButton = (Button) findViewById(R.id.rentabike);
        mRentACarButton = (Button) findViewById(R.id.rentacar);
        mFoodButton = (Button) findViewById(R.id.foodmenu);
        mBusiness = (Button) findViewById(R.id.business);


        // Set click listeners
        mTaxiButton.setOnClickListener(this);
        mWakeUpCallButton.setOnClickListener(this);
        mTransferButton.setOnClickListener(this);
        mDryCleaningButton.setOnClickListener(this);
        mRentABikeButton.setOnClickListener(this);
        mRentACarButton.setOnClickListener(this);
        mFoodButton.setOnClickListener(this);
        mBusiness.setOnClickListener(this);

        // Configure Google Sign In

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.taxi:
                startActivity(new Intent(this, TaxiActivity.class));
                return;
            case R.id.wakeupcall:
                startActivity(new Intent(this, WakeUpCallActivity.class));
                return;
            case R.id.rentabike:
                startActivity(new Intent(this, RentABikeActivity.class));
                return;
            case R.id.rentacar:
                startActivity(new Intent(this, RentACarActivity.class));
                return;
            case R.id.transfer:
                startActivity(new Intent(this, TransferActivity.class));
                return;

            case R.id.drycleaning:
                startActivity(new Intent(this, LaundryActivity.class));
                return;

            case R.id.foodmenu:
                startActivity(new Intent(this, LaundryActivity.class));
                return;

            case R.id.business:
                startActivity(new Intent(this, BusinessActivity.class));
                return;

            case R.id.pillow:
                startActivity(new Intent(this, PillowActivity.class));
                return;

            case R.id.roomservice:
                startActivity(new Intent(this, BusinessActivity.class));
                return;

            default:
                break;
        }

    }




    private void signIn() {

    }


}
