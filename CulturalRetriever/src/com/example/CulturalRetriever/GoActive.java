package com.example.CulturalRetriever;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.location.Location;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class GoActive extends FragmentActivity {
	private GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.activity_go_active);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);
	    Timer beat = new Timer();
	    TimerTask task = new MoveBeat();
	    beat.scheduleAtFixedRate(task, 5000, 10000);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
	
	public class MoveBeat extends TimerTask{
		private Location old = map.getMyLocation();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Location loc = map.getMyLocation();
			
			if(loc != null && old != null &&
			(loc.getLatitude() != old.getLatitude() || loc.getLongitude() != old.getLongitude())){
				map.addPolyline(new PolylineOptions().add(new LatLng(loc.getLatitude(),loc.getLongitude())
					,new LatLng(old.getLatitude(), old.getLongitude())));
			}
			if(loc != null){
				old = loc;
			}
		}
	}
}
