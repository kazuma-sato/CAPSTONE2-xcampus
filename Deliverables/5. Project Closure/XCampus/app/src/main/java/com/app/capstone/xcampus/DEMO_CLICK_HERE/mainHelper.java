package com.app.capstone.xcampus.DEMO_CLICK_HERE;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.app.capstone.xcampus.dataLayer.GlobalVolley;
import com.app.capstone.xcampus.dataLayer.Services;
import com.app.capstone.xcampus.dataObjects.Favourite;
import com.app.capstone.xcampus.dataObjects.Notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * This class contains the methods used in the real application
 *
 * Not all are here but the concept is the same for the ones that are not
 *
 */






public class mainHelper {



    // register
    private void register(Context context){
        // this uses the AWS cognito sdk


        ClientConfiguration clientConfiguration = new ClientConfiguration();

        CognitoUserPool userPool = new CognitoUserPool(context, "us-west-2_9SoJmSN74", "15lmvchrkih1hs0p25mvf7f1i7", "t4dsrr4j4bepie9bcut46ltq05qupp6972l7d8ksce3onnmhstu", clientConfiguration);

        CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        /*
        userAttributes.addAttribute("email", emailInput.getText().toString());
        userAttributes.addAttribute("name",emailInput.getText().toString());
        userAttributes.addAttribute("preferred_username", emailInput.getText().toString());
        userAttributes.addAttribute("given_name", emailInput.getText().toString());
        */

        //userPool.signUpInBackground(emailInput.getText().toString(), passwordInput.getText().toString(), userAttributes, null, handler);


        //Services.getUserFavourites(context);
        //Services.getAllUsers(context);


    }


    // get entries
    public static void getAllUserEntries(final Context context){

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


    // get favourites
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



}
