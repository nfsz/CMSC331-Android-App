package com.example.CulturalRetriever;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Controls extends Fragment {



	ImageButton landmark;
	ImageButton stop;
	
	private TextView timer;
	private TextView timerMS;
	private Handler mHandler = new Handler();
	private long startTime;
	private long elapsedTime;
	private final int REFRESH_RATE = 100;
	private String hours,minutes,seconds,milliseconds;
	private long secs,mins,hrs;
	private boolean stopped = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_controls,
				container, false);

		landmark = (ImageButton) rootView.findViewById(R.id.landmark);
		stop = (ImageButton) rootView.findViewById(R.id.stop);

		
		timer = (TextView) rootView.findViewById(R.id.timer); 
		timerMS = (TextView) rootView.findViewById(R.id.timerMs); 

		landmark.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LandmarkIt.class);
				startActivity(intent);
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.exit(0);
			}
		});

		
		if(stopped){
    		startTime = System.currentTimeMillis() - elapsedTime; 
    	}
    	else{
    		startTime = System.currentTimeMillis();
    	}
    	mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);
        
		return rootView;

	}

	private Runnable startTimer = new Runnable() {
	 	   public void run() {
	 		   elapsedTime = System.currentTimeMillis() - startTime;
	 		   updateTimer(elapsedTime);
	 	       mHandler.postDelayed(this,REFRESH_RATE);
	 	   }
	 	};
	
	 	private void updateTimer (float time){
			secs = (long)(time/1000);
			mins = (long)((time/1000)/60);
			hrs = (long)(((time/1000)/60)/60);
			
			/* Convert the seconds to String 
			 * and format to ensure it has
			 * a leading zero when required
			 */
			secs = secs % 60;
			seconds=String.valueOf(secs);
	    	if(secs == 0){
	    		seconds = "00";
	    	}
	    	if(secs <10 && secs > 0){
	    		seconds = "0"+seconds;
	    	}
	    	
			/* Convert the minutes to String and format the String */
	    	
	    	mins = mins % 60;
			minutes=String.valueOf(mins);
	    	if(mins == 0){
	    		minutes = "00";
	    	}
	    	if(mins <10 && mins > 0){
	    		minutes = "0"+minutes;
	    	}
			
	    	/* Convert the hours to String and format the String */
	    	
	    	hours=String.valueOf(hrs);
	    	if(hrs == 0){
	    		hours = "00";
	    	}
	    	if(hrs <10 && hrs > 0){
	    		hours = "0"+hours;
	    	}
	    	
	    	/* Although we are not using milliseconds on the timer in this example
	    	 * I included the code in the event that you wanted to include it on your own
	    	 */
	    	milliseconds = String.valueOf((long)time);
	    	if(milliseconds.length()==2){
	    		milliseconds = "0"+milliseconds;
	    	}
	      	if(milliseconds.length()<=1){
	    		milliseconds = "00";
	    	}
			milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);
	    	
			/* Setting the timer text to the elapsed time */
			timer.setText(hours + ":" + minutes + ":" + seconds);
			timerMS.setText("." + milliseconds);
		}
	 	
		public void onPause() { 
			super.onPause();
	    	mHandler.removeCallbacks(startTimer);
	    	stopped = true;
		}
	 	
	 	
}
