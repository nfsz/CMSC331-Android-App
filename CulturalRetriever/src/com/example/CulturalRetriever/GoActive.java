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
//	    beat.scheduleAtFixedRate(task, 5000, 10000);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}

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
