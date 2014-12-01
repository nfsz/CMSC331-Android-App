package com.example.CulturalRetriever;

import android.location.Location;
import android.os.Bundle;

public class LocationListener implements android.location.LocationListener{
	private static Location lastKnown;
	
	public static Location GetLocation(){
		return lastKnown; 
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lastKnown = location;
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
