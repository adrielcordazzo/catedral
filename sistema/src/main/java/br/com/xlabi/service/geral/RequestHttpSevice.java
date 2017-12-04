package br.com.xlabi.service.geral;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class RequestHttpSevice {

	@SuppressWarnings("deprecation")
	public String putRequest(String url, String json) {

		HttpClient client = new DefaultHttpClient();
		HttpPut request = new HttpPut(url);

		StringEntity input = null;
		try {
			if (json != null) {
				System.out.println("Put:" + json.toString());
				input = new StringEntity(json.toString());
				if (input != null) {
					input.setContentType("application/json");
					request.setEntity(input);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return response(client, request);
	}

	public String postRequest(String url, String json) {
		System.out.println(url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader(new BasicHeader("Prama", "no-cache"));
		request.setHeader(new BasicHeader("Cache-Control", "no-cache"));
		ArrayList<NameValuePair> postParameters;
		postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("dados", json));

		try {
			request.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response(client, request);
	}

	@SuppressWarnings("deprecation")
	public String getRequest(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		return response(client, request);
	}

	@SuppressWarnings("deprecation")
	public String deleteRequest(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpDelete request = new HttpDelete(url);

		return response(client, request);
	}

	public String response(HttpClient client, HttpRequestBase request) {
		String r = null;
		try {
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			r = EntityUtils.toString(entity, "UTF-8");

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println("erro 1 ");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("erro 2 ");
			e.printStackTrace();
			return null;
		}

		return r;

	}

}
