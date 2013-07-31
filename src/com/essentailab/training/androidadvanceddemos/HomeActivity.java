package com.essentailab.training.androidadvanceddemos;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.essentailab.training.androidadvanceddemos.adapter.SimpleListAdapter;
import com.essentailab.training.androidadvanceddemos.entities.DrawerItem;

public class HomeActivity extends ActionBarActivity {

	private String[] mDrawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mDrawerTitles = getResources().getStringArray(R.array.act_home_drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.act_home_drawerlayout);
        mDrawerList = (ListView) findViewById(R.id.act_home_drawer);
        
        mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(
        		this,
                mDrawerLayout,         
                R.drawable.ic_drawer,
                R.string.act_home_drawer_open,
                R.string.act_home_drawer_close
                ) {

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
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
	    mDrawerList.setItemChecked(position, true);
	    mTitle=mDrawerTitles[position];
	    getSupportActionBar().setTitle(mTitle);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
	    Toast.makeText(getApplicationContext(), "Pressed: "+mTitle, Toast.LENGTH_LONG).show();
	}
}
