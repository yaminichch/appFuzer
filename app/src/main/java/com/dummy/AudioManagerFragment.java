package com.dummy;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yamini on 4/7/18.
 */

public class AudioManagerFragment extends Fragment {


    private Button modeBtn;

    private Button increaseBtn;

    private Button decreaseBtn;

    private RadioButton normal;

    private RadioButton silent;

    private RadioGroup ringGroup;

    private TextView status;

    private AudioManager myAudioManager;


    public static Fragment getInstance() {
        return new AudioCaptureFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_audio_capture, container, false);
        init(view);



        normal = (RadioButton) view.findViewById(R.id.radioNormal);

        silent = (RadioButton) view.findViewById(R.id.radioSilent);

        status = (TextView) view.findViewById(R.id.text);

        ringGroup = (RadioGroup) view.findViewById(R.id.radioRinger);



        modeBtn = (Button)view.findViewById(R.id.mode);

        modeBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                int selectedId = ringGroup.getCheckedRadioButtonId();




                if(selectedId == silent.getId()) {

                    silentEnable(v);

                } else if(selectedId == normal.getId()) {

                    normalEnable(v);

                } else {

                    vibrateEnable(v);

                }

            }

        });



        increaseBtn = (Button)view. findViewById(R.id.increase);

        increaseBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {


                myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

                Toast.makeText(getContext(), "increase volume",

                        Toast.LENGTH_SHORT).show();

            }

        });



        decreaseBtn = (Button) view.findViewById(R.id.decrease);

        decreaseBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {


                myAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

                Toast.makeText(getContext(), "decrease volume",

                        Toast.LENGTH_SHORT).show();

            }

        });


        myAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


        return view;
    }



    public void vibrateEnable(View view){


        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

        status.setText("Current Status: Vibrate Mode");

    }

    public void normalEnable(View view){

        // set the ring mode to loud

        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        status.setText("Current Status: Ring Mode");

    }

    public void silentEnable(View view){


        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        status.setText("Current Status: Silent Mode");

    }
    private void init(final View view) {

    }


}
