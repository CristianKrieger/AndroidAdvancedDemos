package com.krieger.training.androidadvanceddemos.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essentailab.training.androidadvanceddemos.R;
import com.krieger.training.androidadvanceddemos.entities.ImageElement;

public class ViewPagerFragment extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		List<ImageElement> gallery = new ArrayList<ImageElement>();
		gallery.add(new ImageElement("Image 1", "http://www.krieger-electronics.com/images/carousel%20(1).jpg"));
		gallery.add(new ImageElement("Image 2", "http://www.krieger-electronics.com/images/carousel%20(2).jpg"));
		gallery.add(new ImageElement("Image 3", "http://www.krieger-electronics.com/images/carousel%20(3).jpg"));
		gallery.add(new ImageElement("Image 4", "http://www.krieger-electronics.com/images/carousel%20(4).jpg"));
		gallery.add(new ImageElement("Image 5", "http://www.krieger-electronics.com/images/carousel%20(5).jpg"));
		
		View view = inflater.inflate(R.layout.fragment_gallery, container, false);
		ViewPager mPager = (ViewPager) view.findViewById(R.id.gallery_pager);
		PagerAdapter mPagerAdapter = new GalleryPagerAdapter(
				getChildFragmentManager(), gallery);
		mPager.setAdapter(mPagerAdapter);
		return view;
	}
	
	private class GalleryPagerAdapter extends FragmentPagerAdapter {
		private List<ImageElement> urls;
		
		public GalleryPagerAdapter(FragmentManager fm, List<ImageElement> urls) {
			super(fm);
			this.urls = urls;
		}
		
		@Override
		public Fragment getItem(int position) {
			return GalleryPageFragment.create(position, urls.get(position)
					.getUrl());
		}
		
		@Override
		public int getCount() {
			return urls.size();
		}
	}
}
