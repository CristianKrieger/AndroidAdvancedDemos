package com.krieger.training.androidadvanceddemos.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.krieger.training.androidadvanceddemos.entities.DrawerItem;

public class SimpleListAdapter extends BaseAdapter{
	private ArrayList<DrawerItem> data;
	private LayoutInflater inflater;
	private int layoutId;
	private int thumbId;
	private int textId;
	
	public SimpleListAdapter(ArrayList<DrawerItem> data,
			LayoutInflater inflater, int layoutId, int thumbId, int textId) {
		this.data = data;
		this.inflater = inflater;
		this.layoutId = layoutId;
		this.thumbId = thumbId;
		this.textId = textId;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public DrawerItem getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null)
			convertView = inflater.inflate(layoutId, null);
		DrawerItem current = getItem(position);
		((TextView)convertView.findViewById(textId)).setText(current.title);
		((ImageView)convertView.findViewById(thumbId)).setImageDrawable(current.icon);
		return convertView;
	}

}
