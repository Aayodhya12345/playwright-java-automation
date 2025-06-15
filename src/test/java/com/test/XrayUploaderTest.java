/*package com.test;
import com.microsoft.playwright.*;
import com.utilities.XrayUploaderUtils;

import org.testng.annotations.Test;

public class XrayUploaderTest {

    @Test
    public void loginTestAndUploadToXray() {
    	XrayUploaderUtils xray = new XrayUploaderUtils();
        StringBuilder token = xray.getAuthToken();

        // Clean token (strip surrounding quotes)
        if (token != null && token.length() >= 2 &&
            token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"') {
            token.deleteCharAt(token.length() - 1);
            token.deleteCharAt(0);
        }

        //System.out.println("Token: " + token);

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Navigate to the login page
            page.navigate("https://www.saucedemo.com/v1/");
            page.fill("#user-name", "standard_user");
            page.fill("#password", "secret_sauce");
            page.click("#login-button");

            String testKey = "PROJ02-8"; // Xray test issue key
            /*String resultStatus = page.waitForSelector(".inventory_list").isVisible() ? "PASSED" : "FAILED";
            
            
            System.out.println("Test result: " + resultStatus);
            XrayUploader.uploadTestResult(testKey, resultStatus, token);
            
            boolean isSuccess = page.waitForSelector(".inventory_list").isVisible();

            if (isSuccess) {
                System.out.println("✅ Login test passed");
                XrayUploaderUtils.uploadTestResult(testKey, "PASSED", token);
            } else {
                System.out.println("❌ Login test failed");
                XrayUploaderUtils.uploadTestResult(testKey, "FAILED", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkButtonEnabled() throws Exception
    {
    	XrayUploaderUtils xray = new XrayUploaderUtils();
        StringBuilder token = xray.getAuthToken();
        String status;
        String testKey = "PROJ02-10";
        // Clean token (strip surrounding quotes)
        if (token != null && token.length() >= 2 &&
            token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"') {
            token.deleteCharAt(token.length() - 1);
            token.deleteCharAt(0);
        }

        //System.out.println("Token: " + token);

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            
            page.navigate("https://www.saucedemo.com/v1/");
            
            Locator loginbtn = page.locator("//*[@id=\"login-butto\"]");
            
            if(loginbtn.count()==0)
            {
            	//throw new Exception("Sorry, login button not found");
            	XrayUploaderUtils.uploadTestResult(testKey, "FAILED", token);
            	status="FAILED";
            	
            }
            Boolean isEnabled=loginbtn.isEnabled();
            if(isEnabled)
            {
            	System.out.println("PASSED,LOGIN BUTTON IS ENABLED");
            	XrayUploaderUtils.uploadTestResult(testKey, "PASSED", token);
            	status="PASSED";
            }else {
            	System.out.println("Failed, login button diabled");
            	XrayUploaderUtils.uploadTestResult(testKey, "FAILED", token);
            	status="FAILED";
            }
      
    
        }
}
     
}
*/