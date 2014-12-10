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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Explore extends FragmentActivity implements LocationListener {
	private GoogleMap map;
	private LocationManager locationManager;
	private String[][] results;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    map.setMyLocationEnabled(true);

    	map.animateCamera(CameraUpdateFactory.newLatLngZoom
	    		(new LatLng(39.255901,-76.711338), 10));
	    try{	
	    	SQLRequest myRequest = new SQLRequest();
			try {
				results = myRequest.execute(7,7).get();
				for(int count = 0; count < results.length; count++){
					double lat = Double.parseDouble(results[count][5]);
					double lon = Double.parseDouble(results[count][4]);
					String des = results[count][0];
					MarkerOptions lan = new MarkerOptions();
					lan.position(new LatLng(lat, lon));
					lan.title(des);
					map.addMarker(lan).showInfoWindow();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		
	    }catch(Exception e){}
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
