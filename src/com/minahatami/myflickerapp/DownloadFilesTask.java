package com.minahatami.myflickerapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadFilesTask extends AsyncTask<String, Integer, String> {

	private static final String TAG = "DownloadFilesTask";

	private Activity activity;

	public DownloadFilesTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected String doInBackground(String... urls) {
		/*
		 * String response = ""; for (String url : urls) { DefaultHttpClient
		 * client = new DefaultHttpClient(); HttpGet httpGet = new HttpGet(url);
		 * try { HttpResponse execute = client.execute(httpGet); InputStream
		 * content = execute.getEntity().getContent();
		 * 
		 * BufferedReader buffer = new BufferedReader( new
		 * InputStreamReader(content)); String s = ""; while ((s =
		 * buffer.readLine()) != null) { response += s; }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } Log.v(TAG,
		 * "response: " + response.length()); return response;
		 */

		try {
			Log.v(TAG, "" + urls[0]);

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urls[0]);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			BufferedHttpEntity buf = new BufferedHttpEntity(entity);

			InputStream is = buf.getContent();

			BufferedReader r = new BufferedReader(new InputStreamReader(is));

			StringBuilder total = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line + "\n");
			}
			String result = total.toString();
			// Log.i("Get URL", "Downloaded string: " + result);
			Log.v(TAG, "response: " + result.length());
			return result;

		} catch (Exception e) {
			Log.e("Get Url", "Error in downloading: " + e.toString());
		}
		return null;

	}

	@Override
	protected void onPostExecute(String result) {
		//super.onPostExecute(result);
		((DownloadFilesTaskListener)activity).onNotify(result);
	}

}
