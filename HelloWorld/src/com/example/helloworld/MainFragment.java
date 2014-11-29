package com.example.helloworld;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

 // private OnItemSelectedListener listener;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.activity_main_fragment,
        container, false);
  }
  OnCentralSelectedListener mCallback;

  // Container Activity must implement this interface
  public interface OnCentralSelectedListener {
      public void onImageSelected(int ImgID);
  }

  @Override
  public void onAttach(Activity activity) {
      super.onAttach(activity);
      
      // This makes sure that the container activity has implemented
      // the callback interface. If not, it throws an exception
      try {
          mCallback = (OnCentralSelectedListener) activity;
      } catch (ClassCastException e) {
          throw new ClassCastException(activity.toString()
                  + " must implement OnHeadlineSelectedListener");
      }
  }
  
  public void onImageSelected(View v) {
	  
      // Send the event to the host activity
      mCallback.onImageSelected(v.getId());
  }


}

