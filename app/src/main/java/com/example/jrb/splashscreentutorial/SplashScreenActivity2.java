package com.example.jrb.splashscreentutorial;

/**
 * Created by jrb on 11/1/18.
 */
import com.example.jrb.splashscreentutorial.network.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;


public class SplashScreenActivity2 extends AppCompatActivity{

    String id, name, address;
    private static int Test_TIME_OUT = 3000;
    private boolean internetCheck =true;
    private ProgressBar spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        // Start Http Network call
//        new GetOrFetchData().execute();

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        PostDelayedMethod();


    }

    private void PostDelayedMethod() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean InternetResult = checkConnection();
                if (InternetResult) {

                    // Start Http Network call
        new GetOrFetchData().execute();

//                    Intent intent = new Intent(SplashScreenActivity2.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                } else {
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);


                    DialogAppear();
                }

            }
        }, Test_TIME_OUT);

    }


        public void DialogAppear()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashScreenActivity2.this);

        builder.setTitle("NETWORK ERROR");
        builder.setMessage("No Internet Connectivity");

        //NEGATIVE MESSAGE
        builder.setNegativeButton("EXIT",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        finish();

                    }
                });

        //POSITIVE MESSAGE
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        PostDelayedMethod();

                    }
                });
        builder.show();
            }


    protected boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netiInfo = cm.getActiveNetworkInfo();
        if(netiInfo != null && netiInfo.isConnectedOrConnecting()){
            return true;
        }
        else
            {
                return false;
            }
    }


    public boolean checkConnection(){
        if(isOnline()) {
            return internetCheck;
        }
        else{
            internetCheck =false;
            return internetCheck;
        }
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
