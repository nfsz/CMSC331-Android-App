package com.example.helloworld;


import android.support.v4.view.GestureDetectorCompat;

import android.support.v7.app.ActionBarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import android.view.View;

//import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.ViewFlipper;


public class MainActivity extends ActionBarActivity  
	implements MainFragment.OnCentralSelectedListener, 
	GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener{
	    

 
	private static final String DEBUG_TAG = "TopGestures";
	private ViewFlipper mainFlipper;
	private GestureDetectorCompat mDetector;
	private GestureDetectorCompat usedDetector;
    static final String EXTRA_MESSAGE = "oom.example.helloworld.MESSAGE";
    static final String USERNAME = "oom.example.helloworld.CURRENT_USERNAME";
    static final String LANDMARK_MESSAGE = "oom.example.helloworld.LANDMARK_MESSAGE";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainFlipper = (ViewFlipper) findViewById(R.id.maincontainer);
		//mainFlipper.setOnClickListener(this);
        
        
     // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
       mDetector.setOnDoubleTapListener(this);
       usedDetector = new GestureDetectorCompat(this, new MyGestureListener()); 
       
       
       
       
	}
       
    

	
	
	// get fragment manager
/*	android.app.FragmentManager fm = getFragmentManager();

			mFragment = (MainFragment) getFragmentManager().
			  findFragmentById(R.id.imageButton1);
			if (mFragment==null || ! mFragment.isInLayout()) {
			  // start new Activity
				// add
				android.app.FragmentTransaction ft = fm.beginTransaction();
				ft.add(R.id.maincontainer, new MainFragment());
				//ft.add(R.layout.activity_main_fragment02, null);
				// alternatively add it with a tag
				// trx.add(R.id.your_placehodler, new YourFragment(), "detail");
				ft.commit();
			  }
			else {
			  //fragment.update(...);
			} 

	}
    
 */ 
/*
	
				// add
	android.app.FragmentTransaction ft = fm.beginTransaction();
	ft.add(R.id.maincontainer, new MainFragment());
	// alternatively add it with a tag
	// trx.add(R.id.your_placehodler, new YourFragment(), "detail");
	ft.commit();

	// replace
	android.app.FragmentTransaction ft = fm.beginTransaction();
	ft.replace(R.id.your_placehodler, new MainFragment());
	ft.commit();

	// remove
	Fragment fragment = fm.findFragmentById(R.id.your_placehodler);
	FragmentTransaction ft = fm.beginTransaction();
	ft.remove(fragment);
	ft.commit(); */
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);*/
    	
    	// Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch(); //need to complete
                return true;
            case R.id.action_settings:
                //openSettings(); //need to complete
                return true;
            
            case R.id.action_go_active:
            	goActive(null);
            	return true;
            case R.id.action_landmark_it:
            	goLandmarkIt(null);
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /** Called when the user clicks the Send button */
    public void goActive(View view) {
        Intent intent = new Intent(this, GoActive.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "Let's develop this app \"Boys\", \n\nMatt, this is where we need to have GPS going" +
        		"Nasif, Can you get the music set up in this Activity Class as well?";//editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
        
    public void goLandmarkIt(View view) {
        Intent intent = new Intent(this, LandmarkIt.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "Need to add mCamera functionality";//editText.getText().toString();
        intent.putExtra(LANDMARK_MESSAGE, message);
        startActivity(intent);
    }
    public void onImageSelected(int ImgID) {
        switch( ImgID ){
        case (R.id.imageButton1):
        	goActive(null);
        	return;
        case (R.id.imageButton2):
        	goLandmarkIt(null);
        	return;
        default:
        	//a default could be to skip to next option
        	//mFragment.mFlipper.showNext();
        	return;
        	
        }
        
        /*else if (ImgID == R.id.imageButton2){
        	goLandmarkIt(null);
        }*/
        }
    
    /**
     * gonna try to set up the swipe for center fragment
     */
   /* public boolean onTouch(View view, MotionEvent event){
    	if(view == findViewById(R.id.maincontainer)){
    		onTouchEvent(event);
    	}
    	//what do i return here?
    	return true;
    }
    */
    /**
     * Used for gesture debugging purposes
     */
    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
    	/**
    	 * uncomment for all gestures logged
    	 */
        this.mDetector.onTouchEvent(event);
        this.usedDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) { 
        Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, 
            float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        mainFlipper.animate().setDuration(2000).alpha(0); //might need to tweak the duration
        mainFlipper.setOutAnimation(MainActivity.this, R.anim.abc_slide_out_top);
        mainFlipper.animate().alpha(1);
        mainFlipper.setInAnimation(MainActivity.this, R.anim.abc_slide_in_bottom);
        mainFlipper.showNext();
        
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString()); 
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}
/**
 * 
 * @author Patrick
 *The selective gestures
 */
class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final String DEBUG_TAG = "Gestures"; 
    
    @Override
    public boolean onDown(MotionEvent event) { 
        Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, 
            float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
      //NEED TO FIGURE OUT A GOOD DEFAULT VELOSITY
        //SPECIFICALLY NEED TO IMPLEMENT THE velocityY element.
        //mFragment.mFlipper.showNext();
        //Can open the navigation drawer for velocityX action.
        return true;
    }
}
  /*  public boolean onTouchEvent(MotionEvent event){ 
    	this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event); 
        
        int action = MotionEventCompat.getActionMasked(event);
            
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;      
            default : 
                return super.onTouchEvent(event);
        }      
    }*/


	/**
	 * A placeholder fragment containing a simple view.
	 
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_gonnadelete,
					container, false);
			return rootView;
		}
	}*/

