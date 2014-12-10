package com.example.CulturalRetriever;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MusicPlayer extends Fragment implements SensorEventListener{

	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 2.0;
	private AudioManager amg;
	ImageButton previous;
	ImageButton pause;
	ImageButton play;
	ImageButton next;
	private SeekBar sb;
	private int max;
	private int originalVolume;

	public MusicPlayer() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_music_player,
				container, false);

		mInitialized = false;
		mSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		amg = (AudioManager) this.getActivity().getSystemService(Context.AUDIO_SERVICE);

		originalVolume = amg.getStreamVolume(AudioManager.STREAM_MUSIC);
		max = originalVolume;

		sb = (SeekBar) rootView.findViewById(R.id.maxVolumeSeekBar);
		sb.setProgress(originalVolume);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				max = progress;
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		pause = (ImageButton) rootView.findViewById(R.id.pauseButton);
		play = (ImageButton) rootView.findViewById(R.id.playButton);
		previous = (ImageButton) rootView.findViewById(R.id.previousButton);
		next = (ImageButton) rootView.findViewById(R.id.nextButton);

		if (amg.isMusicActive())
			play.setVisibility(View.GONE);
		else if (!amg.isMusicActive())
			pause.setVisibility(View.GONE);


		previous.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_PREVIOUS);
			}
		});
		pause.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_PAUSE);
				pause.setVisibility(View.GONE);
				play.setVisibility(View.VISIBLE);
			}
		});	

		play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_PLAY);
				play.setVisibility(View.GONE);
				pause.setVisibility(View.VISIBLE);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_NEXT);
			}
		});

		return rootView;
	}

	public void onResume() {
		super.onResume();
		originalVolume = amg.getStreamVolume(AudioManager.STREAM_MUSIC);
		max = originalVolume;
		sb.setProgress(originalVolume);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		if (amg.isMusicActive()){
			play.setVisibility(View.GONE);
			pause.setVisibility(View.VISIBLE);
		}
		else if (!amg.isMusicActive()){
			pause.setVisibility(View.GONE);
			play.setVisibility(View.VISIBLE);
		}
	}


	public void onPause() { 
		super.onPause();
		mSensorManager.unregisterListener(this);
		amg.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, AudioManager.FLAG_VIBRATE);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int run = amg.getStreamVolume(AudioManager.STREAM_MUSIC);
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			/*
			 * tvX.setText("0.0"); tvY.setText("0.0"); tvZ.setText("0.0");
			 */mInitialized = true;
		} else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE)
				deltaX = (float) 0.0;
			if (deltaY < NOISE)
				deltaY = (float) 0.0;
			if (deltaZ < NOISE)
				deltaZ = (float) 0.0;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			if (deltaX > 2 || deltaY > 2 || deltaZ > 2) {
				if (amg.getStreamVolume(AudioManager.STREAM_MUSIC) >= max) {
					amg.adjustStreamVolume(AudioManager.STREAM_MUSIC,
							AudioManager.ADJUST_SAME, AudioManager.FLAG_VIBRATE);
				} else {
					amg.adjustStreamVolume(AudioManager.STREAM_MUSIC,
							AudioManager.ADJUST_RAISE,
							AudioManager.FLAG_VIBRATE);
				}
			} else if (deltaX < 0.1) {
				amg.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						AudioManager.ADJUST_LOWER, AudioManager.FLAG_VIBRATE);
			}

//			if(amg.getStreamVolume(AudioManager.STREAM_MUSIC) == 0 && amg.isMusicActive()){
//				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_PAUSE);
//			}
//			if(amg.getStreamVolume(AudioManager.STREAM_MUSIC) > 0 && !amg.isMusicActive()){
//				sendMediaButton(getActivity().getApplicationContext(), KeyEvent.KEYCODE_MEDIA_PLAY);
//			}

		}		
	}

	private static void sendMediaButton(Context context, int keyCode) {
		KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
		Intent intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
		intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
		context.sendOrderedBroadcast(intent, null);

		keyEvent = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
		intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
		intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
		context.sendOrderedBroadcast(intent, null);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
