package com.example.jrb.splashscreentutorial;

/**
 * Created by jrb on 11/1/18.
 */
import com.example.jrb.splashscreentutorial.network.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SplashScreenActivity2 extends AppCompatActivity{

    String id, name, address;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */


        // Start Http Network call
        new GetOrFetchData().execute();
    }

    /**
     * Async Task to make HTTP calls.
     */
    private class GetOrFetchData extends AsyncTask<Void, Void, Void> {

        ObjectMapper mapper = new ObjectMapper();
        String jsonData;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            JsonParser jsonParser= new JsonParser();
            String json = jsonParser
                    .getJSONFromUrl("http://10.1.3.147:8080/topic");

            if (json != null) {
                jsonData = json;
            }

            /*Log.e("Response: ", "> " + json);


            }*/

            // Write the code here to make HTTP calls....
            // For the sake of simplicity of the post, I am only mentioning what to write here and not the actual code.
            // Once your HTTP calls are complete, onPostExecute method will be called with the intended result.
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // This method will be called after completion of HTTP call. So, Retrieve the intended data
            // and start the main activity. You can also pass this data to main activity using putExtra or
            // some similar methods.
            // Make sure you close this activity after starting MainActivity.


            // Start MainActivity...
            Intent i = new Intent(SplashScreenActivity2.this, MainActivity.class);
            i.putExtra("parse", jsonData);

            startActivity(i);

            // Close This Activity
            finish();
        }

    }
}
