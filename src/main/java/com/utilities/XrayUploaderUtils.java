package com.utilities;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class XrayUploaderUtils {

    private static final String XRAY_API_URL = "https://xray.cloud.getxray.app/api/v2";
    private static final String TOKEN_URL = XRAY_API_URL+"/authenticate"; // Update with actual Jira OAuth endpoint
    private static final String CLIENT_ID = "C0B240DC5ADB4894B6E3FBD7FFAC46EA";
    private static final String CLIENT_SECRET = "9808e7853c30c60a3de21828a1802434420826d66d952bedd0ec54680cd99366";

    public static void main(String[] args) {
    	
    	XrayUploaderUtils xray=new XrayUploaderUtils();
    	StringBuilder token = xray.getAuthToken();
    	if(token.length()>=2 && token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"') {
    		token.deleteCharAt(token.length() - 1);
    		token.deleteCharAt(0);
    	}
    	
    	System.out.println(token+"--------");
    	
    	try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

    	// Navigate to login page
        page.navigate("https://www.saucedemo.com/v1/");

        // Fill in login credentials
         page.fill("#user-name", "standard_user");
         page.fill("#password", "secret_sauce");
         page.click("#login-button");

        // Verify login success
        if (page.waitForSelector(".inventory_list").isVisible()) {
           // System.out.println("Login successful!");
            uploadTestResult("PROJ02-10", "PASSED", token);
        } else {
            System.out.println("Login failed!");
            uploadTestResult("PROJ02-10", "FAILED", token);
        }

    	}
    	
	} 
    public static void uploadTestResult(String testKey, String status, StringBuilder token) {
        try {
            String jsonPayload = "{"
                    + "\"testExecutionKey\": \"PROJ02-6\","
                    + "\"tests\": ["
                    + "{"
                    + "\"testKey\": \"" + testKey + "\","
                    + "\"status\": \"" + status + "\""
                    + "}"
                    + "]"
                    + "}";

            URL url = new URL(XRAY_API_URL+"/import/execution");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token.toString().replace("\"", ""));
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 202) {
                //System.out.println("✅ Test result uploaded successfully.");
            } else {
               // System.out.println("❌ Failed to upload result. HTTP Code: " + responseCode);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Error Response: " + line);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static StringBuilder getAuthToken() {
        try {
            // Create JSON payload with credentials
            String jsonPayload = "{ \"client_id\": \"" + CLIENT_ID + "\", \"client_secret\": \"" + CLIENT_SECRET + "\", \"grant_type\": \"client_credentials\" }";

            // HTTP Connection
            URL url = new URL(TOKEN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            
           // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            //Read response
            InputStream is = conn.getInputStream();
           
            Scanner scanner=new Scanner(is);
            String responseBody = scanner.next();
            try {
                //System.out.println("Response Body: " + responseBody); // Debug print
   
                StringBuilder sb = new StringBuilder(responseBody); 
                return sb;
              
            } catch (JSONException e) {
                e.printStackTrace();
                throw new RuntimeException("Unexpected response format! Check API call.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
            
        } 
        
        
    }
		
   

