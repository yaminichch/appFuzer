package com.dummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by yamini on 4/6/18.
 */

public class SmsFragment extends Fragment {

    private EditText phoneNumber;

    private EditText smsBody;

    private Button smsManager;

    private Button smsSIntent;

    private Button smsVIntent;

    public static Fragment getInstance() {
        return new SmsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_sms, container, false);
        init(view);



        phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);

        smsBody = (EditText) view.findViewById(R.id.smsBody);

        smsManager = (Button) view. findViewById(R.id.smsManager);

        smsSIntent= (Button)  view.findViewById(R.id.smsSIntent);

        smsVIntent = (Button) view. findViewById(R.id.smsVIntent);



        smsManager.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {




                sendSmsByManager();


            }

        });



        smsSIntent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                sendSmsBySIntent();

            }

        });



        smsVIntent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                sendSmsByVIntent();

            }

        });




        return view;
    }

    private void init(final View view) {

    }


    public void sendSmsByManager() {




        try {

            // Get the default instance of the SmsManager

            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phoneNumber.getText().toString(),

                    null,

                    smsBody.getText().toString(),

                    null,

                    null);

            Toast.makeText(getContext(), "Your sms has successfully sent!",

                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {

            Toast.makeText(getContext(),"Your sms has failed...",

                    Toast.LENGTH_LONG).show();

            ex.printStackTrace();

        }

    }

    public void sendSmsBySIntent() {

        // add the phone number in the data

        Uri uri = Uri.parse("smsto:" + phoneNumber.getText().toString());



        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

        // add the message at the sms_body extra field

        smsSIntent.putExtra("sms_body", smsBody.getText().toString());

        try{

            startActivity(smsSIntent);

        } catch (Exception ex) {

            Toast.makeText(getContext(), "Your sms has failed...",

                    Toast.LENGTH_LONG).show();

            ex.printStackTrace();

        }

    }



    public void sendSmsByVIntent() {



        Intent smsVIntent = new Intent(Intent.ACTION_VIEW);

        // prompts only sms-mms clients

        smsVIntent.setType("vnd.android-dir/mms-sms");



        // extra fields for number and message respectively

        smsVIntent.putExtra("address", phoneNumber.getText().toString());

        smsVIntent.putExtra("sms_body", smsBody.getText().toString());

        try{

            startActivity(smsVIntent);

        } catch (Exception ex) {

            Toast.makeText(getContext(), "Your sms has failed...",

                    Toast.LENGTH_LONG).show();

            ex.printStackTrace();

        }



    }



}

