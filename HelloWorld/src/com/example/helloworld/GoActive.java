package com.example.helloworld;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class GoActive extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(14);
	    textView.setText(message);
	    
	    
	    // Set the text view as the activity layout
	    //setContentView(R.layout.activity_go_active);
	    setContentView(textView);
	    
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.go_active_activity_actions, menu);
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
                //openSearch(); //need to complete
                return true;
            case R.id.action_settings:
                //openSettings(); //need to complete
                return true;
            case R.id.action_home:
            	goHome();
            	return true;
            case R.id.action_go_active:
            	//should be able to remove this!
            	return true;
            case R.id.action_landmark_it:
            	goLandmarkIt();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
	}
	public void goHome(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	public void goLandmarkIt(){
		Intent intent = new Intent(this, LandmarkIt.class);
	    startActivity(intent);
	}
	 /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.fragment_display_message,
                      container, false);
              return rootView;
        }
    }*/
}
