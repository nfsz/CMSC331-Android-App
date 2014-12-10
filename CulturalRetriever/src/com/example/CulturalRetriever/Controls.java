package com.example.CulturalRetriever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Controls extends Fragment {

	ImageButton landmark;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_controls,
				container, false);
		
		landmark = (ImageButton) rootView.findViewById(R.id.landmark);
		
		landmark.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LandmarkIt.class);
				startActivity(intent);
			}
		});
		
		return rootView;

	}

}
