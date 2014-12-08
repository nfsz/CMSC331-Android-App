package com.example.CulturalRetriever;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class SQLRequest extends AsyncTask<Integer, Void, String[][]> {
	private static final String TAG = "REQUEST_TAG";
	protected void onPreExecute() {

	}
	@Override
	protected String[][] doInBackground(Integer... params) {
	try {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(
				"http://userpages.umbc.edu/~mcpat1/331/androidSQLRequestPage.php");
		//client.execute(request);
		HttpResponse response = client.execute(request);
		Log.d(TAG, ""+response);
//		HttpEntity entity = response.getEntity();
//		String htmlResponse = EntityUtils.toString(entity);
//		
		BufferedReader in = new BufferedReader(new InputStreamReader(
			response.getEntity().getContent()));
		
		
		int rl = params[0];
		int cl = params[1];
		StringBuffer sb = new StringBuffer("");
		String[][] results = new String[rl][cl];
		String line = "";
		int row = 0;
		int column = 0;
		while ((line = in.readLine()) != null) {
			results[row][column] = line;
			column++;
			if (column == cl){
				column = 0;
				row++;
			}
		}
		return results;

		// //////////////////////////////
	} catch (Exception e) {
		Log.d(TAG, "This is the exception within DatabaseActivity "+e);
	}

	return null;

}

}
