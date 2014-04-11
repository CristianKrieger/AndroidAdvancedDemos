package com.krieger.training.androidadvanceddemos.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListOnItemClickListener implements OnItemClickListener{
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		Toast.makeText(view.getContext(),
				"Pressed: "+Integer.toString(position),
				Toast.LENGTH_SHORT).show();
	}
}
