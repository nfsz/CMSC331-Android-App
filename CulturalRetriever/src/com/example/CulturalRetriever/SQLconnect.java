package com.example.CulturalRetriever;



import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;

public class SQLconnect extends AsyncTask<String, Void, Void> {
	protected void onPreExecute() {

	}

	@Override
	protected Void doInBackground(String... params) {
		// "http://userpages.umbc.edu/~mcpat1/331/androidSQLSubmitPage.php";
		try {
			
			String tfDescription = (String) params[0];
			String tfExpirationDate = (String) params[1];
			String linker = (String) params[2];
			String latitude = (String) params[3];
			String longitude = (String) params[4];
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(
					"http://userpages.umbc.edu/~mcpat1/331/androidSQLSubmitPage.php?tfDescription="
							+ tfDescription + "&tfExpirationDate="+tfExpirationDate+"&linker="+linker+
							"&latitude="+latitude+"&longitude="+longitude);
			client.execute(request);

		} catch (Exception e) {

		}
		return null;
	}
}
