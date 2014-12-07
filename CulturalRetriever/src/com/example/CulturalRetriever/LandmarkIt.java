package com.example.CulturalRetriever;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import android.widget.TextView;

public class LandmarkIt extends Activity {
	private EditText landmarkDescription;
	private DatePicker datePicker;
	private Calendar calendar;
	private TextView dateView;
	private int year, month, day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dispatchTakePictureIntent();
		galleryAddPic();

		// NEED TO ADD A SWITCH STATEMENT TO FIND WHO WAS THE PARENT.
		// IF: MainActivity
		// THEN: not active, get details of landmark
		// ELSE: goActive
		// THEN: save image state and gps location for later.

		// after this is unnecessary...
		// Get the message from the intent
		// Intent intent = getIntent();
		// String message =
		// intent.getStringExtra(MainActivity.LANDMARK_MESSAGE);

		// Create the text view
		// TextView textView = new TextView(this);
		// textView.setTextSize(14);
		// textView.setText(message);

		// Set the text view as the activity layout
		// //////setContentView(textView);

		setContentView(R.layout.activity_landmark_it);
		landmarkDescription = (EditText) findViewById(R.id.response_landmark_description);
		dateView = (TextView) findViewById(R.id.textView3);
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		showDate(year, month + 1, day);

	}

	public void startSQL(View view) {
		String desc = landmarkDescription.getText().toString();
		String expDate = dateView.getText().toString();
		String linker = mCurrentPhotoPath;
		new SQLconnect().execute(desc, expDate, linker );
		Intent intent = new Intent(this, DatabaseActivity.class);
		startActivity(intent);
	}

	// @Override
	// public void onClick(View v) {

	// switch (v.getId()) {
	// case R.id.button_submit_landmark:
	// String desc = landmarkDescription.getText().toString();
	// String expDate = getText(R.id.response_expiration).toString();
	// new SQLconnect().execute(desc); //add espDate
	// Intent intent = new Intent(this, DatabaseActivity.class);
	// startActivity(intent);
	// break;
	// case R.id.response_landmark_description:
	// break;
	// }
	// }

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * super.onCreateOptionsMenu(menu); // Inflate the menu; this adds items to
	 * the action bar MenuInflater inflater = getMenuInflater();
	 * inflater.inflate(R.menu.landmark_it_activity_actions, menu); return true;
	 * }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. switch (item.getItemId()) { case
	 * R.id.action_settings: return true; case R.id.button_submit_landmark:
	 * submitToDB(null); return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

	@SuppressWarnings("deprecation")
	public void setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		}
		return null;
	}

	   private DatePickerDialog.OnDateSetListener myDateListener
	   = new DatePickerDialog.OnDateSetListener() {

	   @Override
	   public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
	      // TODO Auto-generated method stub
	      // arg1 = year
	      // arg2 = month
	      // arg3 = day
	      showDate(arg1, arg2+1, arg3);
	   }
	   };

	private void showDate(int year, int month, int day) {
		dateView.setText(new StringBuilder().append(month).append("/")
				.append(day).append("/").append(year));
	}

	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		return image;
	}

	static final int REQUEST_TAKE_PHOTO = 1;

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
				mCurrentPhotoPath = photoFile.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
			} catch (IOException ex) {
				// Error occurred while creating the File
				ex.printStackTrace();
				photoFile = null;
				mCurrentPhotoPath = null;
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}
		private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        	finish();
	        } else {
	            // Image capture failed, advise user
	        	Toast.makeText(this, "Landmark Capture Failed, consider restarting app"
	        			,Toast.LENGTH_LONG).show();
	        }
	    }
	}
	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	/*
	 * private void submitToDB(View view) { // this is a background event... //
	 * might return success status of submission. Intent intent = new
	 * Intent(this, DatabaseActivity.class); startActivity(intent); }
	 */

}
