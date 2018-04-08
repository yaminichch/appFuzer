package com.dummy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by yamini on 4/6/18.
 */

public class AudioCaptureFragment extends Fragment {

    private MediaRecorder myRecorder;

    private MediaPlayer myPlayer;

    private String outputFile = null;

    private Button startBtn;

    private Button stopBtn;

    private Button playBtn;

    private Button stopPlayBtn;

    private TextView text;

    public static Fragment getInstance() {
        return new AudioCaptureFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_audio_capture, container, false);
        init(view);
        text = (TextView) view.findViewById(R.id.text1);


        // store it to sd card

        outputFile = Environment.getExternalStorageDirectory().

                getAbsolutePath() + "/AppFuzer.3gpp";



        myRecorder = new MediaRecorder();

        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        myRecorder.setOutputFile(outputFile);



        startBtn = (Button)view.findViewById(R.id.start);

        startBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO},
                            0);

                } else {
                    start(v);
                }



            }

        });



        stopBtn = (Button)view.findViewById(R.id.stop);

        stopBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                stop(v);

            }

        });



        playBtn = (Button)view.findViewById(R.id.play);

        playBtn.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                play(v);

            }

        });



        stopPlayBtn = (Button)view.findViewById(R.id.stopPlay);
        stopPlayBtn.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {


                stopPlay(v);

            }

        });

        return view;

    }


    public void start(View view){

        try {

            myRecorder.prepare();

            myRecorder.start();

        } catch (IllegalStateException e) {

            // start:it is called before prepare()

            // prepare: it is called after start() or before setOutputFormat()

            e.printStackTrace();

        } catch (IOException e) {

            // prepare() fails

            e.printStackTrace();

        }



        text.setText("Recording Point: Recording");
        startBtn.setEnabled(false);

        stopBtn.setEnabled(true);



        Toast.makeText(getContext(), "Start recording...",

                Toast.LENGTH_SHORT).show();

    }



    public void stop(View view){

        try {

            myRecorder.stop();

            myRecorder.release();

            myRecorder  = null;



            stopBtn.setEnabled(false);

            playBtn.setEnabled(true);

            text.setText("Recording Point: Stop recording");


            Toast.makeText(getContext(), "Stop recording...",

                    Toast.LENGTH_SHORT).show();

        } catch (IllegalStateException e) {

            //  it is called before start()

            e.printStackTrace();

        } catch (RuntimeException e) {

            // no valid audio/video data has been received

            e.printStackTrace();

        }

    }



    public void play(View view) {
        try{

            myPlayer = new MediaPlayer();

            myPlayer.setDataSource(outputFile);

            myPlayer.prepare();

            myPlayer.start();



            playBtn.setEnabled(false);

            stopPlayBtn.setEnabled(true);

            text.setText("Recording Point: Playing");



            Toast.makeText(getContext(), "Start play the recording...",

                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    public void stopPlay(View view) {

        try {

            if (myPlayer != null) {

                myPlayer.stop();

                myPlayer.release();

                myPlayer = null;

                playBtn.setEnabled(true);

                stopPlayBtn.setEnabled(false);

                text.setText("Recording Point: Stop playing");



                Toast.makeText(getContext(), "Stop playing the recording...",

                        Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {


            e.printStackTrace();

        }


    }
    private void init(final View view) {

    }







}
