package backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class EmailVerifier {
	
	public static URL url = null;
	public static InputStream is = null;
	public static DataInputStream dis = null;
	public static JSONObject result = null;
	public static String urlReq = "http://apilayer.net/api/check? access_key=e292e09b28310d3b46c252f491769a34&email=";
	public static String addEmail = "& email = ";
	public static String addSMTP = "& smtp = 1";
	public static String addFORMAT = "& format = 1";
	
	static public Boolean validate(String email) {
		
		if(!email.contains("@usc.edu"))
			return false;
		
		String request = urlReq + email + addSMTP + addFORMAT;
		System.out.println("request: " + request);
			try {
				
				url = new URL(request);
				is = url.openStream();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dis = new DataInputStream(is);
			System.out.println(2);
			BufferedReader reader0 = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText0;
			String mxF = "";
			try {
				jsonText0 = readAll(reader0);
				System.out.println(2);
				System.out.println("jsonText0:\n" + jsonText0);
				JSONObject m0 = new JSONObject(jsonText0);
				mxF = m0.get("mx_found").toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(mxF.equalsIgnoreCase("true"))
				return true;
			else 
				return false;
	}
	
	
	static private String readAll(Reader rdr) throws IOException{
		StringBuilder sb = new StringBuilder();
		
		int cp ;
		while((cp = rdr.read()) != -1){
			sb.append((char)cp);
		}
		
		return sb.toString();
	}
	

}
