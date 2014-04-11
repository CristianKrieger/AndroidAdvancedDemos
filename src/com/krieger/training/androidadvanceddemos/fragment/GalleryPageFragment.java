package com.krieger.training.androidadvanceddemos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.essentailab.training.androidadvanceddemos.R;
import com.krieger.training.androidadvanceddemos.entities.ImageDownloaderCookie;
import com.krieger.training.androidadvanceddemos.io.ImageDownloaderAsyncTask;

public class GalleryPageFragment extends Fragment {

    public static final String ARG_PAGE = "Page";
    public static final String URL = "Url";
    private int mPageNumber;
    private String mImgURL;

    public static GalleryPageFragment create(int pageNumber, String url) {
        GalleryPageFragment fragment = new GalleryPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }
    
    public GalleryPageFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        mImgURL = getArguments().getString(URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_page, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.gallery_img);
		ProgressBar ac = (ProgressBar) rootView.findViewById(R.id.gallery_activitycircle);
		ImageDownloaderCookie cookie=new ImageDownloaderCookie(iv, mImgURL, ac, getActivity());
		ImageDownloaderAsyncTask imgDownloader=new ImageDownloaderAsyncTask();
		imgDownloader.execute(cookie);
        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}