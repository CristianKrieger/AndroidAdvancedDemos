package com.essentailab.training.androidadvanceddemos.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.essentailab.training.androidadvanceddemos.R;
import com.essentailab.training.androidadvanceddemos.adapter.UniversalListAdapter;
import com.essentailab.training.androidadvanceddemos.interfaces.AdapterCommand;

@SuppressWarnings("unchecked")
@SuppressLint("ValidFragment")
public class ListFragment extends Fragment{

	private int rowLayoutId;
	private ArrayList<Object> data;
	private AdapterCommand rowInflationAction;
	
	private int headerLayoutId;
	private AdapterCommand headerInflationAction;
	private boolean hasSections = false;
	
	private OnItemClickListener listener;

	public ListFragment(){}
	
	public ListFragment(int rowLayoutId, Object data,
			AdapterCommand rowInflationAction, OnItemClickListener listener){
		this.rowLayoutId= rowLayoutId;
		this.data= (ArrayList<Object>) data;
		this.rowInflationAction= rowInflationAction;
		this.listener= listener;
	}
	
	public ListFragment(int rowLayoutId, int headerLayoutId,
			Object data, AdapterCommand rowInflationAction,
			AdapterCommand headerInflationAction, OnItemClickListener listener){
		this.rowLayoutId= rowLayoutId;
		this.headerLayoutId = headerLayoutId;
		this.headerInflationAction =  headerInflationAction;
		this.data=(ArrayList<Object>) data;
		this.rowInflationAction= rowInflationAction;
		this.listener= listener;
		hasSections = true;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list, container, false);
		
		ListView sessionsList = (ListView) v.findViewById(R.id.frag_sessions_lv);
		if(hasSections)
			sessionsList.setAdapter(new UniversalListAdapter(inflater,
					rowLayoutId,
					data,
					rowInflationAction, hasSections,
					headerLayoutId, headerInflationAction));
		else
			sessionsList.setAdapter(new UniversalListAdapter(inflater,
					rowLayoutId,
					data,
					rowInflationAction));
		sessionsList.setOnItemClickListener(listener);
		return v;
	}
}
