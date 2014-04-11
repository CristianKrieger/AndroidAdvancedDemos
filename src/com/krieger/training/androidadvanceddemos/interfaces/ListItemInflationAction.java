package com.krieger.training.androidadvanceddemos.interfaces;

import android.view.View;
import android.widget.TextView;

import com.essentailab.training.androidadvanceddemos.R;

public class ListItemInflationAction implements AdapterCommand{
	@Override
	public View execute(Object data, View v) {
		((TextView)v.findViewById(R.id.row_list_txt_title)).setText((String)data);
		return v;
	}
}
