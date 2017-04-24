package com.app.capstone.xcampus.dataLayer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pavle on 2017-03-12.
 */


public class GlobalVolley {

    private static GlobalVolley sInstance;
    private RequestQueue mRequest;


    private static Context mContext;


    private GlobalVolley(Context context){

        mContext = context;

        mRequest = getRequestQueue();

    }

    public static synchronized GlobalVolley getsInstance(Context context){
        if(sInstance == null) {
            sInstance = new GlobalVolley(context);
        }
        return sInstance;
    }


    public RequestQueue getRequestQueue(){
        if(mRequest == null) {
            mRequest = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequest;
    }

    public <T> void addToRequestQueue(Request req){
        getRequestQueue().add(req);
    }

}

