package com.example.CulturalRetriever;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity implements
		MainFragment.OnCentralSelectedListener,
		GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
	// private ImageButton mImageView;
	private static final String DEBUG_TAG = "TopGestures";
	private ViewFlipper mainFlipper;
	private ViewFlipper picFlipper;
	private GestureDetectorCompat mDetector;
	private int rl = 7;
	private int cl = 7;
	private String[][] results;
	private boolean photosSet = false;

	// private GestureDetectorCompat usedDetector;
	// private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainFlipper = (ViewFlipper) findViewById(R.id.maincontainer);
		picFlipper = (ViewFlipper) findViewById(R.id.piccontainer);
		// mImageView = (ImageView) findViewById(R.id.imageView1);

		// Instantiate the gesture detector with the
		// application context and an implementation of

		mDetector = new GestureDetectorCompat(this, this);
		// Set the gesture detector as the double tap
		// listener.
		mDetector.setOnDoubleTapListener(this);
		// usedDetector = new GestureDetectorCompat(this, new
		// MyGestureListener());
		// LocationManager loc = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		// loc.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, new
		// LocationListener());
		picFlipper.setVisibility(View.GONE);

	}

	protected void addDBPics() {
		SQLRequest myRequest = new SQLRequest();
		try {
			results = myRequest.execute(rl, cl).get();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		}

		// Get the photo path
		setPic(results);

	}

	/*
	 * 
	 * // add android.app.FragmentTransaction ft = fm.beginTransaction();
	 * ft.add(R.id.maincontainer, new MainFragment()); // alternatively add it
	 * with a tag // trx.add(R.id.your_placehodler, new YourFragment(),
	 * "detail"); ft.commit();
	 * 
	 * // replace android.app.FragmentTransaction ft = fm.beginTransaction();
	 * ft.replace(R.id.your_placehodler, new MainFragment()); ft.commit();
	 * 
	 * // remove Fragment fragment = fm.findFragmentById(R.id.your_placehodler);
	 * FragmentTransaction ft = fm.beginTransaction(); ft.remove(fragment);
	 * ft.commit();
	 */
	private void setPic(String[][] results) {

		ImageButton mImageView1 = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton mImageView2 = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton mImageView3 = (ImageButton) findViewById(R.id.imageButton3);
		ImageButton mImageView4 = (ImageButton) findViewById(R.id.imageButton4);
		ImageButton mImageView5 = (ImageButton) findViewById(R.id.imageButton5);
		ImageButton mImageView6 = (ImageButton) findViewById(R.id.imageButton6);
		ImageButton mImageView7 = (ImageButton) findViewById(R.id.imageButton7);

		// Get the dimensions of the View

		int targetW = mainFlipper.getWidth();
		int targetH = mainFlipper.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(results[0][3], bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap1 = BitmapFactory.decodeFile(results[0][3], bmOptions);
		mImageView1.setImageBitmap(bitmap1);
		Bitmap bitmap2 = BitmapFactory.decodeFile(results[1][3], bmOptions);
		mImageView2.setImageBitmap(bitmap2);
		Bitmap bitmap3 = BitmapFactory.decodeFile(results[2][3], bmOptions);
		mImageView3.setImageBitmap(bitmap3);
		Bitmap bitmap4 = BitmapFactory.decodeFile(results[3][3], bmOptions);
		mImageView4.setImageBitmap(bitmap4);
		Bitmap bitmap5 = BitmapFactory.decodeFile(results[4][3], bmOptions);
		mImageView5.setImageBitmap(bitmap5);
		Bitmap bitmap6 = BitmapFactory.decodeFile(results[5][3], bmOptions);
		mImageView6.setImageBitmap(bitmap6);
		Bitmap bitmap7 = BitmapFactory.decodeFile(results[6][3], bmOptions);
		mImageView7.setImageBitmap(bitmap7);
		// change visibilities
		mainFlipper.setVisibility(View.GONE);
		picFlipper.setVisibility(View.VISIBLE);

	}

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

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			// openSearch(); //need to complete
			return true;
		case R.id.action_settings:
			// openSettings(); //need to complete
			return true;
			// case R.id.action_view_map:
			// viewMap(null);
			// return true;
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
		startActivity(intent);
	}

	public void viewMap(View view) {
		Intent intent = new Intent(this, Explore.class);
		startActivity(intent);
	}

	public void goLandmarkIt(View view) {
		Intent intent = new Intent(this, LandmarkIt.class);
		startActivity(intent);
	}

	public void onImageSelected(int ImgID) {
		switch (ImgID) {
		case (R.id.imageButton1):
			goActive(null);
			return;
		case (R.id.imageButton2):
			goLandmarkIt(null);
			return;
		default:
			// a default could be to skip to next option
			// mFragment.mFlipper.showNext();
			return;
		}
	}

	/**
	 * Used for gesture debugging purposes
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/**
		 * uncomment for all gestures logged
		 */
		this.mDetector.onTouchEvent(event);
		// this.usedDetector.onTouchEvent(event);
		// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDown: " + event.toString());
		return true;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
		if (photosSet) {
			if (event1.getY() - event2.getY() > 0) {
				picFlipper.animate().setDuration(2000).alpha(0); // might need
																	// to
																	// tweak the
																	// duration
				picFlipper.setOutAnimation(MainActivity.this,
						R.anim.abc_slide_out_top);
				picFlipper.animate().alpha(1);
				picFlipper.setInAnimation(MainActivity.this,
						R.anim.abc_slide_in_bottom);
				picFlipper.showNext();
			} else {
				picFlipper.animate().setDuration(2000).alpha(0); // might need
																	// to
																	// tweak the
																	// duration
				picFlipper.setOutAnimation(MainActivity.this,
						R.anim.abc_slide_out_bottom);
				picFlipper.animate().alpha(1);
				picFlipper.setInAnimation(MainActivity.this,
						R.anim.abc_slide_in_top);
				picFlipper.showPrevious();
			}
		} else {
			if (event1.getY() - event2.getY() > 0) {
				mainFlipper.animate().setDuration(2000).alpha(0); // might need
																	// to
																	// tweak the
																	// duration
				mainFlipper.setOutAnimation(MainActivity.this,
						R.anim.abc_slide_out_top);
				mainFlipper.animate().alpha(1);
				mainFlipper.setInAnimation(MainActivity.this,
						R.anim.abc_slide_in_bottom);
				mainFlipper.showNext();
			} else {
				mainFlipper.animate().setDuration(2000).alpha(0); // might need
																	// to
																	// tweak the
																	// duration
				mainFlipper.setOutAnimation(MainActivity.this,
						R.anim.abc_slide_out_bottom);
				mainFlipper.animate().alpha(1);
				mainFlipper.setInAnimation(MainActivity.this,
						R.anim.abc_slide_in_top);
				mainFlipper.showPrevious();
			}
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
		if (!photosSet) {
			addDBPics();
			photosSet = true;
		}
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
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
