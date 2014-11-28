package com.example.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.EditText;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity  {

    static final String EXTRA_MESSAGE = "oom.example.helloworld.MESSAGE";
    static final String USERNAME = "oom.example.helloworld.CURRENT_USERNAME";
    static final String LANDMARK_MESSAGE = "oom.example.helloworld.LANDMARK_MESSAGE";
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	}
  ///////////////////////////////////////////////////////////////////      
     // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
      /*  if (findViewById(R.id.main_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            HeadlinesFragment firstFragment = new HeadlinesFragment();
            
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());
            
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }*/
/////////////////////////////////////////////////////////////////////////////////////
    


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
        String message = "Let's develop this app \"Boys\", \n\nMatt, this is where we need to have GPS going";//editText.getText().toString();
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
}
