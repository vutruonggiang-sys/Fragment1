package com.rikkei.training.fragment1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;

public class Fragment2 extends Fragment {
    MediaPlayer mediaPlayer;
    SeekBar sBar;
    TextView tvSumTime, tvCurrentTime;
    MainActivity mainActivity;
    private static final String TAG = "FRAGMENT 2";

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        mainActivity = (MainActivity) getActivity();
        sBar = view.findViewById(R.id.sBarTime);
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        tvSumTime = view.findViewById(R.id.tvSumTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        mediaPlayer = MediaPlayer.create(mainActivity, R.raw.devuong);
        mediaPlayer.start();
        if (savedInstanceState != null) {
            mediaPlayer.seekTo((int) savedInstanceState.getLong("currentTime", 0));
            tvCurrentTime.setText(simpleDateFormat.format(savedInstanceState.getLong("currentTime", 0)));
            sBar.setProgress((int) savedInstanceState.getLong("currentTime", 0));
        } else {
            mediaPlayer.seekTo(0);
        }
        tvSumTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sBar.setMax(mediaPlayer.getDuration());
        Log.d(TAG, "FRAGMENT 2 onCreateView");
        displayCurrentTime();
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        return view;
    }

    public void displayCurrentTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvCurrentTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                sBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 300);
            }
        }, 100);
    }

    @Override
    public void onStop() {
        super.onStop();
        mediaPlayer.stop();
        Log.d(TAG, "FRAGMENT 2 onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "FRAGMENT 2 onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "FRAGMENT 2 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
        Log.d(TAG, "FRAGMENT 2 onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "FRAGMENT 2 onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "FRAGMENT 2 onSaveInstanceState");
        outState.putLong("currentTime", mediaPlayer.getCurrentPosition());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "FRAGMENT 2 onViewStateRestored");
    }
}
