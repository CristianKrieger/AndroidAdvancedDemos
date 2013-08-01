package com.essentailab.training.androidadvanceddemos.interfaces;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.essentailab.training.androidadvanceddemos.R;
import com.essentailab.training.androidadvanceddemos.entities.ListElementCookie;

public class AsyncListInflationAction implements AdapterCommand{
	@Override
	public View execute(Object data, View v) {
		((TextView) v.findViewById(R.id.listview_txt_top)).
			setText(((ListElementCookie) data).getTitle());
		
		((TextView) v.findViewById(R.id.listview_txt_bottom)).
			setText(((ListElementCookie) data).getDescription());
		
		((ImageView) v.findViewById(R.id.listview_img)).
			setImageDrawable(((ListElementCookie) data).getThumb());
		return v;
	}
}