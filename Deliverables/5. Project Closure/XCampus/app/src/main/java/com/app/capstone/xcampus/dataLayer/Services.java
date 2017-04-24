package com.app.capstone.xcampus.dataLayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.capstone.xcampus.LoginActivity;
import com.app.capstone.xcampus.activities.MainActivity;
import com.app.capstone.xcampus.dataObjects.Entry;
import com.app.capstone.xcampus.dataObjects.Favourite;
import com.app.capstone.xcampus.dataObjects.Notification;
import com.app.capstone.xcampus.dataObjects.Rating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.entries;
import static android.R.attr.reqFiveWayNav;
import static android.R.attr.textViewStyle;

/**
 * Created by pavle on 2017-03-12.
 */

public class Services {

    public static class AccountManeger{

        public static void saveUser(Context context,String username, String token, String sessid, String timezone){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor storeEditer = store.edit();

            storeEditer.putString("username", username);
            storeEditer.putString("token", token);
            storeEditer.putString("sessid", sessid);
            storeEditer.putString("timezone", timezone);

            storeEditer.commit();

        }

        public static void userCheck(Context context){
            if(getUserName(context) == null) {
                Activity activity = (Activity) context;
                Intent authOut = new Intent(context, LoginActivity.class);
                context.startActivity(authOut);
                activity.finish();
            }
        }

        public static void userCheckEscalate(Context context){
            if(getUserName(context) != null) {
                Activity activity = (Activity) context;
                Intent authOut = new Intent(context, MainActivity.class);
                context.startActivity(authOut);
                activity.finish();
            }
        }

        public static void userCheckDeEscalate(Context context){
            if(getUserName(context) == null) {
                Activity activity = (Activity) context;
                Intent authOut = new Intent(context, LoginActivity.class);
                context.startActivity(authOut);
                activity.finish();
            }
        }

        public static String getUserName(Context context){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);

            return store.getString("username",null);
        }

        public static String getToken(Context context){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);

            return store.getString("token",null);
        }

        public static String getSess(Context context){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);

            return store.getString("sessid", null);
        }

        public static String getTimezone(Context context){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);

            return store.getString("timezone", null);
        }

        public static void removeUser(Context context){
            SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor storeEditer = store.edit();
            storeEditer.clear();
            storeEditer.commit();
        }

    }


    public static void getUserNotes(Context context  ){

        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry/notes";


        JsonArrayRequest notesReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Entry> entries = new ArrayList<>();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }


    public static void getUserFavourites( Context context ){

        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/user/favourite";

        Log.d("url",url);

        JsonArrayRequest userFavsReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //String =

                Log.d("response",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        GlobalVolley.getsInstance(context).addToRequestQueue(userFavsReq);
    }



    public static void getRntry(Context context){
        // get all non comments
        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry";

        JsonArrayRequest reqEntry = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                for(int x = 0; x < response.length(); x++) {
                    try {
                        String parentId = response.getJSONObject(x).getString("parentID");
                    }
                    catch(JSONException ex) {

                        try {
                            String parentId = response.getJSONObject(x).getString("parentID");
                        }
                        catch(JSONException jsEx) {
                            //    System.out.print(response.getJSONObject(x).getString("parentID"));
                        }

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }



    public static void getAllUsers(final Context context){

        //String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry/notes";

        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry";

        JsonArrayRequest reqGetAllUsers = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    for(int x = 0; x < response.length(); x++) {
                        // String entry = response.getJSONObject(0).getString("parentID");\
                        try {
                            // comment
                            String commentId = response.getJSONObject(x).getString("parentID");
                        }
                        catch (JSONException ex){
                            Log.d("current iteration" , String.valueOf(x) );
                            Services.getUserEntryNarrow(context,x);
                        }
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });

        GlobalVolley.getsInstance(context).addToRequestQueue(reqGetAllUsers);
    }

    public static void getUserEntryNarrow(Context context , final int x){

        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry";

        JsonArrayRequest userReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String id = response.getJSONObject(x).getString("parentID");
                    Log.d("test", id);
                }catch (JSONException ex){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.d("eoor",error.toString());
            }
        });

        GlobalVolley.getsInstance(context).addToRequestQueue(userReq);
    }





    public static void getEntries(Context context, final TextView textview){
        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/entry";

        Log.d("firat", url);

        JsonArrayRequest entryReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Entry> entries = new ArrayList<>();

                Log.d("test" , response.toString());

                for( int x = 0; x < response.length(); x++ ) {

                    try {
                        JSONObject entry = response.getJSONObject(x);
                        entries.add(
                                new Entry(entry.getString("id"),
                                        entry.getString("author"),
                                        entry.getString("parentID"),
                                        entry.getString("title"),
                                        entry.getString("entryType") ,
                                        entry.getString("description"),
                                        entry.getString("dateCreated") ,
                                        entry.getString("dateModified"),
                                        entry.getString("courseID") ,
                                        entry.getString("programCode"),
                                        entry.getString("institution"),
                                        entry.getString("startSemester"),
                                        entry.getString("flaggedBy"),
                                        entry.getString("dateFlagged")
                                ));
                    }catch (JSONException ex) {
                        Log.d("error" , ex.toString());
                        textview.setText(ex.toString()); }


                }
                Log.d("finished" , "done");
                textview.setText(String.valueOf(entries.size()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText(error.toString());
                Log.d("error", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        GlobalVolley.getsInstance(context).addToRequestQueue( entryReq );

    }


    public static void getFavs(Context context, final TextView textView){
        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/favourite";

        JsonArrayRequest favsReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Favourite> favs = new ArrayList<>();

                for( int x = 0; x < response.length(); x++ ) {

                    try {
                        JSONObject fav = (JSONObject) response.get(x);

                        favs.add(new Favourite(
                                fav.getString("entryID"),
                                fav.getString("userID")
                        ));

                    }catch(JSONException ex) { }

                }

                textView.setText(String.valueOf(favs.size()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        GlobalVolley.getsInstance(context).addToRequestQueue(favsReq);

    }


    public static void getNotifs(Context context , final TextView textView){
        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/notification";

        JsonArrayRequest notifReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Notification> notifs = new ArrayList<>();

                for( int x = 0; x < response.length(); x++ ) {

                    try {
                        JSONObject notification = (JSONObject) response.get(x);

                        notifs.add(new Notification(
                                notification.getString("entryID"),
                                notification.getString("userID"),
                                notification.getString("actionID")
                        ));
                    }
                    catch(JSONException ex){ }

                }

                textView.setText( String.valueOf(notifs.size()) );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        GlobalVolley.getsInstance(context).addToRequestQueue(notifReq);
    }


    public static void getRatings(Context context , final TextView textview){
        String url = "https://or4xc50d36.execute-api.us-west-2.amazonaws.com/dev/rating";

        JsonArrayRequest ratingReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Rating> ratings = new ArrayList<>();

                for( int x = 0; x < response.length(); x++ ) {
                    try {
                        JSONObject rating = (JSONObject) response.get(x);

                        ratings.add( new Rating(
                                rating.getString("entryID"),
                                rating.getString("userID")
                        ) );

                    }catch(JSONException ex) {}
                }

                textview.setText( String.valueOf(ratings.size()) );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText(error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        GlobalVolley.getsInstance(context).addToRequestQueue(ratingReq);

    }

}

