package com.krieger.training.androidadvanceddemos.entities;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageDownloaderCookie {
	private ImageView imageView;
	private String URL;
	private ProgressBar progressBar;
	private Context ctx;
	
	public ImageDownloaderCookie(ImageView iv, String URL, ProgressBar progressBar, Context ctx){
		this.imageView=iv;
		this.URL=URL;
		this.progressBar=progressBar;
		this.ctx=ctx;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public String getURL() {
		return URL;
	}
	
	public ProgressBar getProgressBar(){
		return progressBar;
	}
	
	public Context getContext(){
		return ctx;
	}
}
