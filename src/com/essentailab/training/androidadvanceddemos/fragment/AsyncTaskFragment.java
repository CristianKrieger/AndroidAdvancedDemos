package com.essentailab.training.androidadvanceddemos.fragment;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.essentailab.training.androidadvanceddemos.R;
import com.essentailab.training.androidadvanceddemos.adapter.UniversalListAdapter;
import com.essentailab.training.androidadvanceddemos.entities.ListElementCookie;
import com.essentailab.training.androidadvanceddemos.interfaces.AsyncListInflationAction;

public class AsyncTaskFragment extends Fragment{
	private ListView lv;
	private ProgressBar pb;
	private TextView pb_txt;
	private View pb_container;
	private LayoutInflater inflater;
	private ThumbAsyncTask task;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater=inflater;
		View v = inflater.inflate(R.layout.fragment_asynctask, container, false);
		lv = (ListView) v.findViewById(R.id.frag_asynctask_lv);
		pb = (ProgressBar) v.findViewById(R.id.frag_asynctask_pb);
		pb_txt = (TextView) v.findViewById(R.id.frag_asynctask_txt);
		pb_container = v.findViewById(R.id.frag_asynctask_container_pb);
		
		
		String[] img_urls = {
			"http://images.krieger-electronics.com/sample1.png",
			"http://images.krieger-electronics.com/sample2.png",
			"http://images.krieger-electronics.com/sample3.png",
			"http://images.krieger-electronics.com/sample4.png",
			"http://images.krieger-electronics.com/sample5.png",
		};
		
		String[] urls = new String[20];
		for(int i=0; i<urls.length;i++)
			urls[i]=img_urls[i%(img_urls.length)];
		
		task = new ThumbAsyncTask(getActivity());
		task.execute(urls);
		
		return v;
	}
	
	@Override
	public void onStop() {
		task.cancel(true);
		super.onStop();
	}

	private void setProgress(int progress, int total){
		float pgrss = ((float)progress)/((float)total);
		float t_pgrss = pgrss*((float)pb.getMax());
		pb.setProgress((int)t_pgrss);
		pb_txt.setText("Downloaded: "+Integer.toString(progress)
				+" of "+Integer.toString(total));
	}
	
	private void inflateList(final ArrayList<Object> data){
		pb_container.setVisibility(View.GONE);
		lv.setVisibility(View.VISIBLE);

		lv.setAdapter(new UniversalListAdapter(inflater,
				R.layout.row_listview,
				data,
				new AsyncListInflationAction()));
		
		OnItemClickListener clickHandler = new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        Toast.makeText(v.getContext(), "You pressed: "
		        		+((ListElementCookie)data.get(position))
		        		.getTitle(), Toast.LENGTH_LONG).show();
		    }
		};
		lv.setOnItemClickListener(clickHandler);
	}
	
	private class ThumbAsyncTask extends AsyncTask<String, Integer,
			ArrayList<Object>>{

		private int count;
		private Context c;
		
		public ThumbAsyncTask(Context c){
			this.c=c;
		}
		
		@Override
		protected ArrayList<Object> doInBackground(String... urls) {
			count = urls.length;
			
			ArrayList<Object> data = new ArrayList<Object>();
			publishProgress(0);
			
			for (int i = 0; i < count; i++) {
				if (isCancelled()) break;
				ListElementCookie cookie = new ListElementCookie(i,
						downloadImage(urls[i]),
						"Item "+Integer.toString(i),
						"Lorem ipsum dolor sit amet, consectetur "
						+"adipisicing elit, sed do eiusmod tempor "
						+"incididunt ut labore et dolore magna aliqua.");
				
				data.add(cookie);
				publishProgress(i+1);
			}
			return data;
		}
		
		protected void onProgressUpdate(Integer... progress) {
			setProgress(progress[0], count);
		}

	    protected void onPostExecute(ArrayList<Object> result) {
	    	inflateList(result);
	    }
	    
	    @Override
		protected void onCancelled() {
			Toast.makeText(c, "Download was interrupted!", Toast.LENGTH_LONG).show();
			super.onCancelled();
		}

		private Drawable downloadImage(String url){
			try {
				InputStream is = (InputStream) new URL(url).getContent();
				return Drawable.createFromStream(is, "img");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
