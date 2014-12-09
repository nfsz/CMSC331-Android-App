package com.example.CulturalRetriever;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.FragmentActivity;
import android.support.v7.appcompat.R.color;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoActive extends FragmentActivity implements LocationListener{
	private GoogleMap map; 
	private LocationManager locationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.activity_go_active);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);
	    Timer beat = new Timer();
	    TimerTask task = new MoveBeat();
	    Location start = getLocation();
	    map.animateCamera(CameraUpdateFactory.newLatLngZoom
	    		(new LatLng(start.getLatitude(), start.getLongitude()), 10));
	    beat.scheduleAtFixedRate(task, 5000, 10000);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}

	public class MoveBeat extends TimerTask{
		private Location old;
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Location loc = getLocation();
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

	public Location getLocation() { 
		String provider = null;
		  locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		  if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			  provider = LocationManager.GPS_PROVIDER;
		  } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			  provider = LocationManager.NETWORK_PROVIDER;
		  } else { 
			  // FAIL 
		  } 
		  locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
		  return locationManager.getLastKnownLocation(provider);
	  }
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
