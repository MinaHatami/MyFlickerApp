package com.minahatami.myflickerapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadGenricTask<T> extends AsyncTask<String, Integer, T>{

	private static final String TAG = "DownloadImageTask";

	@Override
	protected T doInBackground(String... urls) {
		Bitmap bitmap = null;
		Log.v(TAG, "urls[0]: " + urls[0]);
	    try {
	    	
	        URL url = new URL(urls[0]);
	        URLConnection urlConnection = url.openConnection();
	        urlConnection.connect();
	        // TODO: T might be a String
	        InputStream inputStream = urlConnection.getInputStream();
	        BufferedInputStream bufferedIS = new BufferedInputStream(inputStream);
	        bitmap = BitmapFactory.decodeStream(bufferedIS);
	        
	        bufferedIS.close();
	        inputStream.close();
	        
	    } catch (IOException e) {
	        Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
	    } 
	    
	    return (T)bitmap;
	}

}
