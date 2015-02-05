package com.rjil.jio.nasan.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONObject;

public class updateclient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		updateclient c= new updateclient();
		
		//c.updateClinicInfo("rf1232","RF", "Ganesh", "vashi", "navi mumbai", "988428613", "", "sdfs@fortis.com", "");
		
		//c.updatedeviceInfo("samsung s4","120987","1","56");
		
		c.deletedeviceclient("56");
	}

	public JSONObject updateClinicInfo(String clinic_id, String password,
			String organisation_name, String organisation_head,
			String organisation_address, String city, String contact_no,
			String alternate_contact_no, String email_id,
			String alternate_email_id) 
	{

		JSONObject data = new JSONObject();
		try {
			
			String path = "http://localhost:8080/jioecg_1401/rest/jioecg/updateclinicinformation?clinic_id="
					+ URLEncoder.encode(clinic_id)
					+ "&password="
					+ URLEncoder.encode(password)
					+ "&organisation_name="
					+ URLEncoder.encode(organisation_name)
					+ "&organisation_head="
					+ URLEncoder.encode(organisation_head)
					+ "&organisation_address="
					+ URLEncoder.encode(organisation_address)
					+ "&city="
					+ URLEncoder.encode(city)
					+ "&contact_no="
					+ URLEncoder.encode(contact_no)
					+ "&alternate_contact_no="
					+ URLEncoder.encode(alternate_contact_no)
					+ "&email_id="
					+ URLEncoder.encode(email_id)
					+ "&alternate_email_id="
					+ URLEncoder.encode(alternate_email_id) +"";
			System.out.println(path);
			HttpPost post = new HttpPost(path);
			// post.setHeader("Content-type", "application/json");
			// post.setEntity(new StringEntity(json.toString(), "UTF-8"));
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse httpresponse = client.execute(post);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpresponse.getEntity().getContent()));
			String line = br.readLine();

			HttpEntity entity = httpresponse.getEntity();
			System.out.println(line);

			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replaceAll("\"", "");

			String a[] = new String[2];
			for (String retval : line.split(",", 0)) {
				// System.out.println(retval);
				String a1 = retval;
				int i = 0;
				for (String a2 : a1.split(":", 0)) {
					a[i] = a2;
					i++;
				}
				data.put(a[0], a[1]);
			}

			System.out.println("json");
			System.out.println(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	
		
		
	}
	
	
	public JSONObject updatedeviceInfo(String device_name,
			String device_id, String device_status,String clinic_id) 
	{

		JSONObject data = new JSONObject();
		try {
			
			String path = "http://localhost:8080/jioecg_1401/rest/jioecg/updatedeviceinfo?device_name="
					+ URLEncoder.encode(device_name) 
					+ "&device_id="
					+ device_id
					+ "&device_status="
					+ device_status
					+ "&clinic_id="
					+ clinic_id;
			
			System.out.println(path);
			HttpPost post = new HttpPost(path);
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse httpresponse = client.execute(post);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpresponse.getEntity().getContent()));
			
			String line = br.readLine();

			HttpEntity entity = httpresponse.getEntity();
			System.out.println(line);

			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replaceAll("\"", "");

			String a[] = new String[2];
			for (String retval : line.split(",", 0)) {
				// System.out.println(retval);
				String a1 = retval;
				int i = 0;
				for (String a2 : a1.split(":", 0)) {
					a[i] = a2;
					i++;
				}
				data.put(a[0], a[1]);
			}

			System.out.println("json"+data.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String deletedeviceclient(String clinic_id)  {

		JSONObject data = new JSONObject();
		String line="";
		try {
			String path = "http://localhost:8080/jioecg_1401/rest/jioecg/deletedevice?clinic_id="
					+ clinic_id;

			HttpPost post = new HttpPost(path);

			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse httpresponse = client.execute(post);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpresponse.getEntity().getContent()));

			 line = br.readLine();
			System.out.println("line" + line);
			HttpEntity entity = httpresponse.getEntity();

			
		} catch (Exception e) {
			// e.printStackTrace();
		}
		//System.out.println("data" + data);
		return line;
	}
	
	
}
