package com.example.helloworld;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//import android.widget.TextView;

public class LandmarkIt extends Activity implements OnClickListener {
	Button sqlSubmit;
	EditText landmarkDescription;

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
		sqlSubmit = (Button) findViewById(R.id.button_submit_landmark);
		landmarkDescription = (EditText) findViewById(R.id.response_landmark_description);

		sqlSubmit.setOnClickListener(this);
		landmarkDescription.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_submit_landmark:
			String desc = getText(R.id.response_landmark_description)
					.toString();
			String expDate = getText(R.id.response_expiration).toString();
			new SQLconnect().execute(desc, expDate, mCurrentPhotoPath);
			//Intent intent = new Intent(this, DatabaseActivity.class);
			//startActivity(intent);
			break;
		case R.id.response_landmark_description:
			break;
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.landmark_it_activity_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		case R.id.button_submit_landmark:
			submitToDB(null);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

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

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	/*private void submitToDB(View view) {
		// this is a background event...
		// might return success status of submission.
		Intent intent = new Intent(this, DatabaseActivity.class);
		startActivity(intent);
	}*/

}
