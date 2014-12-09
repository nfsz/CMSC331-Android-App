package com.example.CulturalRetriever;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoActive extends FragmentActivity {
	private GoogleMap map;
	private LocationManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.activity_go_active);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);
	    Timer beat = new Timer();
	    TimerTask task = new MoveBeat();
	    manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    beat.scheduleAtFixedRate(task, 5000, 10000);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}

	public class MoveBeat extends TimerTask{
		private Location old;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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
