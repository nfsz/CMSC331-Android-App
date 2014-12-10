package com.example.CulturalRetriever;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Explore extends FragmentActivity implements LocationListener {
	private GoogleMap map;
	private LocationManager locationManager;
	private String[][] results = new String[7][6];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);

	    Location start = getLocation();
	    
	    try{
	    	map.animateCamera(CameraUpdateFactory.newLatLngZoom
	    		(new LatLng(start.getLatitude(), start.getLongitude()), 10));	
	    	SQLRequest myRequest = new SQLRequest();
			try {
				results = myRequest.execute(7,6).get();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} catch (ExecutionException e) {
			
				e.printStackTrace();
			}
		
	    }catch(Exception e){}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.explore, menu);
		return true;
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
