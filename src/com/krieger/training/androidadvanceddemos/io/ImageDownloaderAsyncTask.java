package com.krieger.training.androidadvanceddemos.io;

import java.io.InputStream;
import java.net.URL;

import com.essentailab.training.androidadvanceddemos.R;
import com.krieger.training.androidadvanceddemos.entities.ImageDownloaderCookie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageDownloaderAsyncTask extends AsyncTask<ImageDownloaderCookie, Void, Drawable> {
	ImageView imageView = null;
	ProgressBar activityCircle=null;
	
	@Override
	protected Drawable doInBackground(ImageDownloaderCookie... imageDownloaderCookie) {
	    this.imageView = imageDownloaderCookie[0].getImageView();
	    this.activityCircle = imageDownloaderCookie[0].getProgressBar();
	    return downloadImage(imageDownloaderCookie[0].getURL(),
	    		imageDownloaderCookie[0].getContext());
	}

	@Override
	protected void onPostExecute(Drawable result) {
		this.activityCircle.setVisibility(View.GONE);
		this.imageView.setVisibility(View.VISIBLE);
	    this.imageView.setImageDrawable(result);
	}
	
	public static Drawable downloadImage(String url, Context activity) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "img");
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return activity.getResources().getDrawable(
					R.drawable.placeholder);
		}
	}
}