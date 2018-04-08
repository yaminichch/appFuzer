package com.dummy;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Developer: Saurabh Verma
 * Dated: 05/04/18.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final float SCALE_FACTOR = 8f;
    private static final long BACK_PRESS_DURATION = 2000;
    private DrawerLayout mDrawerLayout;
    private LinearLayoutCompat llMainLayout;
    private long backPressed;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = findViewById(R.id.mDrawerLayout);
        llMainLayout = findViewById(R.id.llMainLayout);

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.setDrawerElevation(0f);


        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull final View drawerView, final float slideOffset) {
                float slideX = drawerView.getWidth() * slideOffset;
                llMainLayout.setTranslationX(slideX);
                llMainLayout.setScaleX(1 - (slideOffset / SCALE_FACTOR));
                llMainLayout.setScaleY(1 - (slideOffset / SCALE_FACTOR));
            }

            @Override
            public void onDrawerOpened(@NonNull final View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull final View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(final int newState) {

            }
        });

        findViewById(R.id.tvCall).setOnClickListener(this);
        findViewById(R.id.tvSms).setOnClickListener(this);
        findViewById(R.id.tvEmail).setOnClickListener(this);
        findViewById(R.id.tvAudioManger).setOnClickListener(this);
        findViewById(R.id.tvAudioCapture).setOnClickListener(this);
        findViewById(R.id.tvBluetooth).setOnClickListener(this);
        findViewById(R.id.ivNav).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.tvCall:
                replaceFragment(CallFragment.getInstance());
                break;
            case R.id.tvSms:
                replaceFragment(SmsFragment.getInstance());
                break;
            case R.id.tvEmail:
                replaceFragment(EmailFragment.getInstance());
                break;
            case R.id.tvAudioManger:
                replaceFragment(AudioManagerFragment.getInstance());
                break;
            case R.id.tvAudioCapture:
                replaceFragment(AudioCaptureFragment.getInstance());
                break;
            case R.id.tvBluetooth:
                break;
            case R.id.ivNav:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            default:
                break;

        }
    }

    /**
     * Replace fragment.
     *
     * @param mFragment the m fragment
     */
    private void replaceFragment(final Fragment mFragment) {
        //Closing drawer on item click
        mDrawerLayout.closeDrawers();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frameContainer, mFragment, null);
                fragmentTransaction.commitAllowingStateLoss();
            }
        }, 200);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            closeDrawer();
            return;
        }
        if (backPressed + BACK_PRESS_DURATION > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

    /**
     * Close drawer.
     */
    private void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }
}
