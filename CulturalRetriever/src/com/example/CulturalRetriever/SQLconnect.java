package com.example.CulturalRetriever;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.location.Location;
import android.os.AsyncTask;

public class SQLconnect extends AsyncTask<String, Void, String> {
	protected void onPreExecute() {

	}
	

	@Override
	protected String doInBackground(String... params) {

		try {
			String tfDescription = (String) params[0];
			String tfExpirationDate = (String) params[1];
			String linker = (String) params[2];
			Location loc = LocationListener.GetLocation();
			String link = "http://userpages.umbc.edu/~mcpat1/331/androidSQLSubmitPage.php";
			String data = URLEncoder.encode("tfDescription", "UTF-8") + "="
					+ URLEncoder.encode(tfDescription, "UTF-8");
			data += "&" + URLEncoder.encode("tfExpirationDate", "UTF-8") + "="
					+ URLEncoder.encode(tfExpirationDate, "UTF-8");
			data += "&" + URLEncoder.encode("linker", "UTF-8") + "="
					+ URLEncoder.encode(linker, "UTF-8");
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setDoInput(true); //conn.setDoOutput(true); //for reading data
			//OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			/*
			 * BufferedReader reader = new BufferedReader
			 * 
			 * (new InputStreamReader(conn.getInputStream())); StringBuilder sb
			 * = new StringBuilder(); String line = null; // Read Server
			 * Response while((line = reader.readLine()) != null) {
			 * sb.append(line); break; } return sb.toString();
			 */
			return null;
		} catch (Exception e) {
			return new String("Exception: " + e.getMessage());
		}
	}
	@Override
	   protected void onPostExecute(String result){
		
	   }
}
/*
 * import java.io.BufferedReader; import java.io.InputStreamReader; import
 * java.io.OutputStreamWriter; import java.net.URI; import java.net.URL; import
 * java.net.URLConnection; import java.net.URLEncoder;
 * 
 * import org.apache.http.HttpResponse; import
 * org.apache.http.client.HttpClient; import
 * org.apache.http.client.methods.HttpGet; import
 * org.apache.http.impl.client.DefaultHttpClient;
 * 
 * import android.content.Context; import android.os.AsyncTask; import
 * android.widget.TextView;
 * 
 * public class SigninActivity extends AsyncTask<String,Void,String>{
 * 
 * private TextView statusField,roleField; private Context context; private int
 * byGetOrPost = 0; //flag 0 means get and 1 means post.(By default it is get.)
 * public SigninActivity(Context context,TextView statusField, TextView
 * roleField,int flag) { this.context = context;; byGetOrPost = flag; }
 * 
 * protected void onPreExecute(){
 * 
 * }
 * 
 * @Override protected String doInBackground(String... arg0) {
 * 
 * try{ String username = (String)arg0[0]; String password = (String)arg0[1];
 * String link="http://userpages.umbc.edu/~mcpat1/331/androidSQLpage.php";
 * String data = URLEncoder.encode("username", "UTF-8") + "=" +
 * URLEncoder.encode(username, "UTF-8"); data += "&" +
 * URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password,
 * "UTF-8"); URL url = new URL(link); URLConnection conn = url.openConnection();
 * conn.setDoOutput(true); OutputStreamWriter wr = new OutputStreamWriter
 * (conn.getOutputStream()); wr.write( data ); wr.flush(); BufferedReader reader
 * = new BufferedReader (new InputStreamReader(conn.getInputStream()));
 * StringBuilder sb = new StringBuilder(); String line = null; // Read Server
 * Response while((line = reader.readLine()) != null) { sb.append(line); break;
 * } return sb.toString(); }catch(Exception e){ return new String("Exception: "
 * + e.getMessage()); } } }
 * 
 * }
 */