package com.essentailab.training.androidadvanceddemos;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.essentailab.training.androidadvanceddemos.adapter.SimpleListAdapter;
import com.essentailab.training.androidadvanceddemos.entities.DrawerItem;
import com.essentailab.training.androidadvanceddemos.entities.HeadedList;
import com.essentailab.training.androidadvanceddemos.fragment.AsyncTaskFragment;
import com.essentailab.training.androidadvanceddemos.fragment.GridViewFragment;
import com.essentailab.training.androidadvanceddemos.fragment.ListFragment;
import com.essentailab.training.androidadvanceddemos.fragment.SimpleFragment;
import com.essentailab.training.androidadvanceddemos.interfaces.ListHeaderInflationAction;
import com.essentailab.training.androidadvanceddemos.interfaces.ListItemInflationAction;
import com.essentailab.training.androidadvanceddemos.listener.ListOnItemClickListener;

public class HomeActivity extends ActionBarActivity {
	
	private final static int FRAG_TYPE_ABOUT = 0;
	private final static int FRAG_TYPE_SIMPLE = 1;
	private final static int FRAG_TYPE_GRID = 2;
	private final static int FRAG_TYPE_WEB = 3;
	private final static int FRAG_TYPE_NESTED = 4;
	private final static int FRAG_TYPE_VIEWPAGER = 5;
	private final static int FRAG_TYPE_ERROR = 6;
	
	private final static String BUNDLE_SAVED_FRAGTAG = "FRAGTAG";
	private final static String BUNDLE_SAVED_TITLE = "TITLE";

	private String[] mDrawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String mTitle;
	private ActionBarDrawerToggle mDrawerToggle;
	private String currentFragTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mDrawerTitles = getResources().getStringArray(R.array.act_home_drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.act_home_drawerlayout);
        mDrawerList = (ListView) findViewById(R.id.act_home_drawer);
        
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction t = fm.beginTransaction();
        if (savedInstanceState!=null){
        	mTitle = savedInstanceState.getString(BUNDLE_SAVED_TITLE);
        	getSupportActionBar().setTitle(mTitle);
        	currentFragTag = savedInstanceState.getString(BUNDLE_SAVED_FRAGTAG);
        	
        }else{
        	mTitle = (String) getTitle();
            currentFragTag = SimpleFragment.class.getName();
            t.add(R.id.act_home_container_root,
            		SimpleFragment.newInstance("HELLO!"),
            		currentFragTag);
        }
        t.commit();
        
        mDrawerToggle = new ActionBarDrawerToggle(
        		this,
                mDrawerLayout,         
                R.drawable.ic_drawer,
                R.string.act_home_drawer_open,
                R.string.act_home_drawer_close){

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        int[] mDrawables = {
        	R.drawable.ic_drawer_about,
        	R.drawable.ic_drawer_list,
        	R.drawable.ic_drawer_grid,
        	R.drawable.ic_drawer_web,
        	R.drawable.ic_drawer_nested,
        	R.drawable.ic_drawer_gallery,
        	R.drawable.ic_drawer_error
        };
        
        ArrayList<DrawerItem> data = new ArrayList<DrawerItem>();
        for(int i=0; i<mDrawerTitles.length; i++)
        	data.add(new DrawerItem(getResources().getDrawable(mDrawables[i]), mDrawerTitles[i]));
        
		mDrawerList.setAdapter(new SimpleListAdapter(data,
        		getLayoutInflater(), R.layout.row_drawer, R.id.row_drawer_img, R.id.row_drawer_txt));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    super.onSaveInstanceState(savedInstanceState);
	    savedInstanceState.putString(BUNDLE_SAVED_FRAGTAG, currentFragTag);
	    savedInstanceState.putString(BUNDLE_SAVED_TITLE, mTitle);
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}

	private void selectItem(int position) {
//		if(position!=FRAG_TYPE_ABOUT && position!=FRAG_TYPE_GRID && position!=FRAG_TYPE_SIMPLE){
//			return;
//		}
	    mDrawerList.setItemChecked(position, true);
	    mTitle=mDrawerTitles[position];
	    getSupportActionBar().setTitle(mTitle);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
	    Toast.makeText(getApplicationContext(), "Pressed: "+mTitle, Toast.LENGTH_LONG).show();
	    
	    FragmentManager fm = getSupportFragmentManager();
	    FragmentTransaction t = fm.beginTransaction();
	    t.remove(fm.findFragmentByTag(currentFragTag));
	    Fragment f = null;
	    
	    switch(position){
	    case FRAG_TYPE_ABOUT:
	    	f = SimpleFragment.newInstance("HELLO!");
	    	currentFragTag = SimpleFragment.class.getName();
	    	break;
	    case FRAG_TYPE_SIMPLE:
		    
			ArrayList<HeadedList<String, String>> info1;
		    info1 = new ArrayList<HeadedList<String,
		    		String>>();
		    for(int i=0; i<5; i++){
		    	ArrayList<String> list =
		    			new ArrayList<String>();
		    	for(int j=0; j<9; j++)
		    		list.add("Hello World");
				info1.add(new HeadedList<String,
						String>("Header "+Integer.toString(i),
		    			list));
		    }
		    
		    f = new ListFragment(R.layout.row_list,
		    		R.layout.header_list,
		    		info1,
		    		new ListItemInflationAction(),
		    		new ListHeaderInflationAction(),
		    		new ListOnItemClickListener());
		    f.setRetainInstance(true);
		    currentFragTag = ListFragment.class.getName();
		    break;
	    case FRAG_TYPE_WEB:
	    	f= new AsyncTaskFragment();
	    	currentFragTag = AsyncTaskFragment.class.getName();
	    	break;
	    case FRAG_TYPE_GRID:
	    	f = new GridViewFragment();
	    	currentFragTag = GridViewFragment.class.getName();
	    	break;
	    }
	    
	    t.add(R.id.act_home_container_root, f, currentFragTag);
        t.commit();
	}
}
