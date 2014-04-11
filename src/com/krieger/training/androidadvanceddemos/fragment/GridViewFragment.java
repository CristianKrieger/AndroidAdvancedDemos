package com.krieger.training.androidadvanceddemos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.essentailab.training.androidadvanceddemos.R;

public class GridViewFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_gridview, container, false);
		GridView gv = (GridView) v.findViewById(R.id.frag_gridview_gv);
		
		gv.setAdapter(new ImageAdapter(inflater));

	    gv.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(v.getContext(), "Pressed: " + Integer.toString(position),
	            		Toast.LENGTH_SHORT).show();
	        }
	    });
		return v;
	}
	
	private class ImageAdapter extends BaseAdapter {
	    private LayoutInflater inflater;

	    public ImageAdapter(LayoutInflater inflater) {
	        this.inflater = inflater;
	    }

	    public int getCount() {
	        return 50;
	    	//return images.length;
	    }

	    public Integer getItem(int position) {
	        return images[position%images.length];
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null)
	        	imageView = (ImageView) inflater.inflate(R.layout.element_gridview, null);
	        else
	            imageView = (ImageView) convertView;

	        imageView.setImageResource(getItem(position));
	        return imageView;
	    }

	    private Integer[] images = {
	            R.drawable.sample_img_blue,
	            R.drawable.sample_img_purple,
	            R.drawable.sample_img_green,
	            R.drawable.sample_img_orange,
	            R.drawable.sample_img_red
	    };
	}
}
