package com.trupt.itrainz.ui.activity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trupt.itrainz.R;

public class HttpActivity extends Activity {
	
	private ImageView imageView;
	private Button buttonSubmit;
	private EditText editText;
	private String sessionId;
	private String additionalParams;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http);
		imageView = (ImageView) findViewById(R.id.imageView);
		editText = (EditText) findViewById(R.id.editText);
		buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
		buttonSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new HttpAsyncTask().execute();
			}
		});
		new InItLoginAsyncTask().execute();
	}
	
	private String getLoginResponse() {
		String response = null;
		try {
			URL url = new URL("https://nget.irctc.co.in/eticketing/home");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("POST");
				
			httpURLConnection.addRequestProperty("Host", "nget.irctc.co.in");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/13.0.1");
			httpURLConnection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,**;q=0.8");
			httpURLConnection.addRequestProperty("Accept-Language", "en-us,en;q=0.5");
			//httpURLConnection.addRequestProperty("Accept-Encoding", "gzip,deflate");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Referer", "https://nget.irctc.co.in/eticketing/loginHome.jsf");	
			httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
			//httpURLConnection.addRequestProperty("Origin", "http://www.indianrail.gov.in");
			
			StringBuilder paramSB = new StringBuilder();
			paramSB.append("j_username=deepdsce"
					+ "&j_password=just%40chill"
					+ "&j_captcha="+ editText.getText().toString() +""
					+ "&submit=Login"
					+ "&" +additionalParams+ "");
			
			httpURLConnection.addRequestProperty("Cookie", sessionId);
			
			byte[] data = paramSB.toString().getBytes("UTF-8");
			
			DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
			dataOutputStream.write(data);
			
			Map<String, List<String>> respHeaders = httpURLConnection.getHeaderFields(); 
			if (respHeaders != null) {
				List<String> head = respHeaders.get("Set-Cookie");
				if(head != null) {
					String session =  getCookie(head);
					sessionId = session;
				}
			}
	    
			InputStream inStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
			StringBuilder sb = new StringBuilder();
			String read = null;
			while((read = bufferedReader.readLine()) != null) {
				sb.append(read);
			}
			response = sb.toString();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	private byte[] getCaptchaImage() {
		byte[] imageData = null;
		try {
			URL url = new URL("https://nget.irctc.co.in/eticketing/captchaImage");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("POST");
				
			httpURLConnection.addRequestProperty("Host", "nget.irctc.co.in");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/13.0.1");
			httpURLConnection.addRequestProperty("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
			httpURLConnection.addRequestProperty("Accept-Language", "en-US,en;q=0.5");
			//httpURLConnection.addRequestProperty("Accept-Encoding", "gzip,deflate");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Referer", "https://nget.irctc.co.in/eticketing/loginHome.jsf");	
			httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
			//httpURLConnection.addRequestProperty("Origin", "http://www.indianrail.gov.in");
			
			httpURLConnection.addRequestProperty("Cookie", sessionId);
			
			Map<String, List<String>> respHeaders = httpURLConnection.getHeaderFields(); 
			if (respHeaders != null) {
				List<String> head = respHeaders.get("Set-Cookie");
				if(head != null) {
					String session =  getCookie(head);
					sessionId = session;
				}
			}			
			
			InputStream inStream = httpURLConnection.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while((readCount = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, readCount);
			}
			imageData = outStream.toByteArray();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return imageData;
	}
	
	private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String response = getLoginResponse();
			Log.e("DEBUG", "RESPONSE: " + response);
			return null;
		}
		
	}

	private class CaptchaAsyncTask extends AsyncTask<Void, Void, byte[]> {

		@Override
		protected byte[] doInBackground(Void... params) {
			byte[] data = getCaptchaImage();
			return data;
		}
		
		@Override
		protected void onPostExecute(byte[] result) {
			super.onPostExecute(result);
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
			imageView.setImageBitmap(bitmap);
		}
	}
	
	private class InItLoginAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
				try {
					URL url = new URL("https://nget.irctc.co.in/eticketing/login.jsf");
					HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
					httpURLConnection.setDoOutput(true);
					httpURLConnection.setDoInput(true);
					httpURLConnection.setConnectTimeout(10000);
					httpURLConnection.setRequestMethod("POST");
						
					httpURLConnection.addRequestProperty("Host", "nget.irctc.co.in");
					httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/13.0.1");
					httpURLConnection.addRequestProperty("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
					httpURLConnection.addRequestProperty("Accept-Language", "en-US,en;q=0.5");
					//httpURLConnection.addRequestProperty("Accept-Encoding", "gzip,deflate");
					httpURLConnection.addRequestProperty("Connection", "keep-alive");
					httpURLConnection.addRequestProperty("Referer", "https://nget.irctc.co.in/eticketing/loginHome.jsf");	
					httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
					//httpURLConnection.addRequestProperty("Origin", "http://www.indianrail.gov.in");
						    
					Map<String, List<String>> respHeaders = httpURLConnection.getHeaderFields(); 
					List<String> head = respHeaders.get("Set-Cookie");
					String session =  respHeaders.get("Set-Cookie").toString();
					sessionId = getCookie(head);
					
					InputStream inStream = httpURLConnection.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
					StringBuilder sb = new StringBuilder();
					String read = null;
					while((read = bufferedReader.readLine()) != null) {
						sb.append(read);
					}
					String response = sb.toString();
					Document document = Jsoup.parse(response);
					Elements eleSfid = document.body().select("input[name=as_sfid]");
					String sfid = eleSfid.first().attr("value");
					Elements eleFid = document.body().select("input[name=as_fid]");
					String fid = eleFid.first().attr("value");
					additionalParams = "as_sfid=" + sfid + "&" + "as_fid=" + fid;
					additionalParams = Uri.encode(additionalParams);
					System.out.println(additionalParams);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				return null;
			}		
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			new CaptchaAsyncTask().execute();
		}
		
	}
	
	private String getCookie(List<String> headers) {
		String cookie = "";
		for(String header : headers) {
			cookie = cookie + header.split(";")[0] + "; ";
		}
		cookie = cookie.replaceAll("(; )$", "");
		return cookie;
	}
}
