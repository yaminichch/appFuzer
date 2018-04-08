package com.dummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by yamini on 4/8/18.
 */

public class EmailFragment extends Fragment {
    private EditText recipient;

    private EditText subject;

    private EditText body;

    public static Fragment getInstance() {
        return new EmailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragmenr_email, container, false);
        init(view);



        recipient = (EditText) view.findViewById(R.id.recipient);

        subject = (EditText) view.findViewById(R.id.subject);

        body = (EditText)view. findViewById(R.id.body);



        Button sendBtn = (Button) view.findViewById(R.id.sendEmail);

        sendBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                sendEmail();

                // after sending the email, clear the fields

                recipient.setText("");

                subject.setText("");

                body.setText("");

            }

        });


        return view;

    }


    private void init(final View view) {

    }


    protected void sendEmail() {



        String[] recipients = {recipient.getText().toString()};

        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        // prompts email clients only

        email.setType("message/rfc822");



        email.putExtra(Intent.EXTRA_EMAIL, recipients);

        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());

        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());



        try {

            // the user can choose the email client

            startActivity(Intent.createChooser(email, "Choose an email client from..."));



        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(getContext(), "No email client installed.",

                    Toast.LENGTH_LONG).show();

        }

    }




}
