package com.example.CulturalRetriever;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DatabaseActivity extends Activity {
	private ImageView mImageView;
	private static final String TAG = "HTTP RESPONSE:";
	private int rl;
	private int cl;
	private String[][] results = new String[rl][cl];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		
		String[] row = {"", "entry1", "entry2", "entry3", "entry4", "entry5",
				"entry6", "entry7" };
		String[] column = {"", "Description", "Date Created", "Expiration Date",
				"Photo URL" };
		rl = row.length;
	
		cl = column.length;

		SQLRequest myRequest = new SQLRequest();
		try {
			results = myRequest.execute(rl, cl).get();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
		
			e.printStackTrace();
		}
		Log.d("--", "R-Lenght--" + rl + "   " + "C-Lenght--" + cl);
		;
		// enabled scroll for large data
		ScrollView sv = new ScrollView(this);
		TableLayout tableLayout = createTableLayout(row, column, rl, cl, results);
		HorizontalScrollView hsv = new HorizontalScrollView(this);

		hsv.addView(tableLayout);
		sv.addView(hsv);
		setContentView(sv);
		


	}
	
	


	public void makeCellEmpty(TableLayout tableLayout, int rowIndex,
			int columnIndex) {
		// get row from table with rowIndex
		TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

		// get cell from row with columnIndex
		TextView textView = (TextView) tableRow.getChildAt(columnIndex);

		// make it black
		textView.setBackgroundColor(Color.BLACK);
	}

	public void setHeaderTitle(TableLayout tableLayout, int rowIndex,
			int columnIndex) {

		// get row from table with rowIndex
		TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

		// get cell from row with columnIndex
		TextView textView = (TextView) tableRow.getChildAt(columnIndex);

		textView.setText("Hello");
	}

	private TableLayout createTableLayout(String[] rv, String[] cv,
			int rowCount, int columnCount, String[][] data) {
		// 1) Create a tableLayout and its params
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setBackgroundColor(Color.BLACK);

		// 2) create tableRow params
		TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
		tableRowParams.setMargins(1, 1, 1, 1);
		tableRowParams.weight = 1;

		for (int i = 0; i < rowCount; i++) {
			// 3) create tableRow
			TableRow tableRow = new TableRow(this);
			tableRow.setBackgroundColor(Color.BLACK);

			for (int j = 0; j < columnCount; j++) {
				// 4) create textView
				TextView textView = new TextView(this);
				// textView.setText(String.valueOf(j));
				textView.setBackgroundColor(Color.WHITE);
				textView.setGravity(Gravity.CENTER);

				String s1 = Integer.toString(i);
				String s2 = Integer.toString(j);
				String s3 = s1 + s2;
				int id = Integer.parseInt(s3);
				Log.d("TAG", "-___>" + id);
				if (i == 0 && j == 0) {
					textView.setText("0==0");
				} else if (i == 0) {
					Log.d("TAAG", "set Column Headers");
					textView.setText(cv[j]);
				} else if (j == 0) {
					Log.d("TAAG", "Set Row Headers");
					textView.setText(rv[i]);
				} else {
					textView.setText(""+data[i-1][j-1]);
				}

				// 5) add textView to tableRow
				tableRow.addView(textView, tableRowParams);
			}

			// 6) add tableRow to tableLayout
			tableLayout.addView(tableRow, tableLayoutParams);
		}

		return tableLayout;
	}
}
