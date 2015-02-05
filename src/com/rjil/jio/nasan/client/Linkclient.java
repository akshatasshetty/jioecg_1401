package com.rjil.jio.nasan.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonObject;

public class Linkclient {
	
	public static void main(String[] args) throws IOException {
		Linkclient l=new Linkclient();
		l.getLink("123","27-05-2014 12:10 PM");
		
		
	}
	
	public String getLink(String patient_id,String time_stamp) throws IOException
	{
		//String patient_id="880";
		String output="";
		String url="http://localhost:8080/jioecg_1401/rest/jioecg/getLink?patient_id="+patient_id+"&time_stamp="+ URLEncoder.encode(time_stamp);
		
		System.out.println("link of url :"+url);
		try{
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-type", "application/json");
			
			//post.setEntity(new StringEntity(json.toString(), "UTF-8"));
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			//System.out.println("status code :"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()!=200){
				System.out.println("Failed: Http error code:"+response.getStatusLine().getStatusCode());
				//System.out.println("error");
			}
			
			
			BufferedReader br=new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			
			
			while((output=br.readLine())!=null){
				System.out.println(output);
			}
			client.getConnectionManager().shutdown();
			//System.out.println(httpresponse);
			/*HttpEntity entity = httpresponse.getEntity();
			System.out.println(entity);*/
			
		}catch(ClientProtocolException ex){
			ex.printStackTrace();
		}
		return output;
		
	}

}
