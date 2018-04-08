package com.dummy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Developer: Saurabh Verma
 * Dated: 05/04/18.
 */
public class CallFragment extends Fragment {


    Button btn;
    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Fragment getInstance() {
        return new CallFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_call, container, false);
        init(view);

        btn = (Button) view.findViewById(R.id.button);



        PhoneCallListener phoneCallListener = new PhoneCallListener();

//        TelephonyManager telManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyManager telManager = (TelephonyManager) this.getActivity().getSystemService(Context.TELEPHONY_SERVICE) ;

        telManager.listen(phoneCallListener, PhoneStateListener.LISTEN_CALL_STATE);



        // add button listener

        btn.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {


                Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);

                phoneCallIntent.setData(Uri.parse("tel:123456"));

                startActivity(phoneCallIntent);


            }
        });
        return view;
    }

    private void init(final View view) {

    }


    // monitor phone call states

    private class PhoneCallListener extends PhoneStateListener {



        String TAG = "LOGGING PHONE CALL";



        private boolean phoneCalling = false;



        @Override

        public void onCallStateChanged(int state, String incomingNumber) {



            if (TelephonyManager.CALL_STATE_RINGING == state) {

                // phone ringing

                Log.i(TAG, "RINGING, number: " + incomingNumber);

            }



            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {

                // active

                Log.i(TAG, "OFFHOOK");



                phoneCalling = true;

            }



            // When the call ends launch the main activity again

            if (TelephonyManager.CALL_STATE_IDLE == state) {



                Log.i(TAG, "IDLE");



                if (phoneCalling) {



                    Log.i(TAG, "restart app");



                    // restart app
                    //Intent i = getBaseContext().getPackageManager()

                            //.getLaunchIntentForPackage(

                              //      getBaseContext().getPackageName());


                    Intent i =getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(i);



                    phoneCalling = false;

                }



            }

        }

    }


}
