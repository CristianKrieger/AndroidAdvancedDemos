package com.krieger.training.androidadvanceddemos.fragment;

import com.essentailab.training.androidadvanceddemos.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ErrorFragment extends Fragment{

	private Bundle b;
	private final static String BUNDLE_STRING = "data";
	
	public static Fragment newInstance(String dataToShow) {
        Fragment f = new ErrorFragment();

        Bundle args = new Bundle();
        args.putString(BUNDLE_STRING, dataToShow);
        f.setArguments(args);
        return f;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		b=getArguments();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_simple, null);
		Toast.makeText(v.getContext(), b.getString(BUNDLE_STRING), Toast.LENGTH_SHORT).show();
		return v;
	}
}
