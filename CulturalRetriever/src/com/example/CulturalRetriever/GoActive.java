package com.example.CulturalRetriever;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoActive extends FragmentActivity implements LocationListener{
	private GoogleMap map; 
	private LocationManager locationManager;
	private Location old;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.activity_go_active);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);

	    Location start = getLocation();
	    old = start;
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	    
	    try{
	    	map.animateCamera(CameraUpdateFactory.newLatLngZoom
	    		(new LatLng(start.getLatitude(), start.getLongitude()), 10));	
	    	
	    }catch(Exception e){}
	    //beat.scheduleAtFixedRate(task, 5000, 10000);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
		if(old != null){
			PolylineOptions po = new PolylineOptions();
			LatLng pointA = new LatLng(location.getLatitude(),location.getLongitude());
			LatLng pointB = new LatLng(old.getLatitude(), old.getLongitude());
			po.add(pointA, pointB);
			po.width(2);
			po.color(Color.RED);
			map.addPolyline(po);
		}	
		old = location;
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
