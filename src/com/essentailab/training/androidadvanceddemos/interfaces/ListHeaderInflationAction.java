package com.essentailab.training.androidadvanceddemos.interfaces;

import android.view.View;
import android.widget.TextView;

import com.essentailab.training.androidadvanceddemos.R;

public class ListHeaderInflationAction implements AdapterCommand{
	@Override
	public View execute(Object data, View v) {
		((TextView)v.findViewById(R.id.header_list_txt)).setText((String)data);
		return v;
	}
}
