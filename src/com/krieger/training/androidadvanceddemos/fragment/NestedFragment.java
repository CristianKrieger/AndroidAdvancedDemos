package com.krieger.training.androidadvanceddemos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essentailab.training.androidadvanceddemos.R;

public class NestedFragment extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_nested, null);
		FragmentManager fm = getChildFragmentManager();
	    FragmentTransaction t = fm.beginTransaction();
	    t.add(R.id.frag_nested_container_1, new AsyncTaskFragment());
	    t.add(R.id.frag_nested_container_2, new ViewPagerFragment());
	    t.commit();
		return v;
	}
}
