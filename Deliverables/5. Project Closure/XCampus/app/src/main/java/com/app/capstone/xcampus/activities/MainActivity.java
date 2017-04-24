package com.app.capstone.xcampus.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.capstone.xcampus.R;
import com.app.capstone.xcampus.fragments.LikesFragment;
import com.app.capstone.xcampus.fragments.MyPostsFragment;
import com.app.capstone.xcampus.fragments.PostsFragment;
import com.app.capstone.xcampus.misc.NavigationDrawer;
import com.app.capstone.xcampus.misc.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);

        mTabLayout.setupWithViewPager(mViewPager);
        setupViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        NavigationDrawer.drawerSet(this, (Activity)this, toolbar, drawer, navigationView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity( new Intent(context , AddPostActivity.class));
            }
        });






    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PostsFragment(), "All Posts");
        adapter.addFrag(new MyPostsFragment(), "My Posts");
        adapter.addFrag(new LikesFragment(), "My Likes");

        viewPager.setAdapter(adapter);
    }
}
