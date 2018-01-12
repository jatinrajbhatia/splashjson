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


public class SplashScreenActivity2 extends AppCompatActivity{

    String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         // Start Http Network call
        new GetOrFetchData().execute();
    }

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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Start MainActivity...
            Intent i = new Intent(SplashScreenActivity2.this, MainActivity.class);
            i.putExtra("parse", jsonData);

            startActivity(i);

            // Close This Activity
            finish();
        }

    }
}
