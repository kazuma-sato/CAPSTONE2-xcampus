package com.app.capstone.xcampus.misc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.capstone.xcampus.LoginActivity;
import com.app.capstone.xcampus.R;
import com.app.capstone.xcampus.activities.AboutUsActivity;
import com.app.capstone.xcampus.activities.ContactUsActivity;
import com.app.capstone.xcampus.activities.MainActivity;

/**
 * Created by pavle on 2017-03-12.
 */


public class NavigationDrawer {



    public static void drawerSet(final Context context, final Activity activity, Toolbar toolbar, DrawerLayout drawer, NavigationView navigationView){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.home) {
                    context.startActivity(new Intent(context, MainActivity.class));
                    //activity.finish();
                }
                else if(id == R.id.notifs) {

                }
                else if(id == R.id.aboutUs){
                    context.startActivity(new Intent(context , AboutUsActivity.class ));
                }
                else if(id == R.id.contactUs) {
                    context.startActivity(new Intent( context , ContactUsActivity.class));
                    //activity.finish();
                }
                else if(id == R.id.logout) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }

                DrawerLayout drawer = (DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });




    }

}

