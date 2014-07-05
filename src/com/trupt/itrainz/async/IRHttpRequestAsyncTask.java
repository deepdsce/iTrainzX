package com.trupt.itrainz.async;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.trupt.itrainz.common.Error;
import com.trupt.itrainz.model.request.HttpRequest;
import com.trupt.itrainz.model.request.PnrRequest;
import com.trupt.itrainz.model.request.Request;
import com.trupt.itrainz.model.result.PnrStatus;
import com.trupt.itrainz.util.HtmlParseUtil;

public class IRHttpRequestAsyncTask<IN extends Request, Result> extends HttpRequestAsyncTask<IN, Result> {
	
	@Override
	protected Result doInBackground(IN... params) {
		Result result = super.doInBackground(params);
		if(result == null) {
			error = new Error(Error.ErrorCodeEnum.FACILITY_NOT_AVAILABLE);
		}
		return result;
	}
	
	@Override
	protected String getResponse(Request req) throws Exception {
		String response = null;
		HttpRequest request = (HttpRequest)req;
		HttpURLConnection httpURLConnection = (HttpURLConnection) request.getUrl().openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setConnectTimeout(10000);
		httpURLConnection.setRequestMethod(request.getMethodEnum().toString());
			
		httpURLConnection.addRequestProperty("Host", "www.indianrail.gov.in");
		httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/13.0.1");
		httpURLConnection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,**;q=0.8");
		httpURLConnection.addRequestProperty("Accept-Language", "en-us,en;q=0.5");
		//httpURLConnection.addRequestProperty("Accept-Encoding", "gzip,deflate");
		httpURLConnection.addRequestProperty("Connection", "keep-alive");
		httpURLConnection.addRequestProperty("Referer", "http://www.indianrail.gov.in/");	
		httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
		//httpURLConnection.addRequestProperty("Origin", "http://www.indianrail.gov.in");
		
		StringBuilder paramSB = new StringBuilder();
		Set<Entry<String, String>> entries = request.getMapParameters().entrySet();
		for(Entry<String, String> entry : entries) {
			paramSB.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		paramSB.deleteCharAt(paramSB.length() - 1);
		
		byte[] data = paramSB.toString().getBytes("UTF-8");
		
		DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
		dataOutputStream.write(data);
    
		InputStream inStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
		StringBuilder sb = new StringBuilder();
		String read = null;
		while((read = bufferedReader.readLine()) != null) {
			sb.append(read);
		}
		response = sb.toString();
		return response;
	}
	
	@Override
	protected String getJsonResponse(String res) {
		String jsonResponse = null;
		Document document = Jsoup.parse(res);
		switch (request.getRequestType()) {
			case PNR_STATUS:
				PnrStatus pnrStatus = HtmlParseUtil.parsePNRData(document, ((PnrRequest)request).getPnrNumber());
				Gson gson = new Gson();
				jsonResponse = gson.toJson(pnrStatus);
				break;
		}
		return jsonResponse;
	}
		
}
