package tests;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.APIUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
 
/**
 * @author mvelayutham
 * 
 * 		API TEST CASE SCRIPT DEMO
 *
 */
public class APIRequestScript {

	public WebDriver driver() {
		WebDriver driver;
		String driverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		return driver;
	}
	
//	@Test(priority=0)
	public void getAllProducts() throws IOException {
		
		// Declaring bearer token and requestUrl
		String bearerToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBwb3J0b3BzQG8xbmJjdW5pLmNvbSIsImRuIjoiQ049c3VwcG9ydG9wc0BvMW5iY3VuaS5jb20sT1U9bzFuYmN1bmksT1U9UUFOZXR3b3JrcyxEQz1vcGVyYXRpdmVvbmUsREM9Y29tIiwicGVybWlzc2lvbnMiOlszNzMsNTA1MjkyODIsMTIyNzEzMzUxMywtMTg0MDcwMDI3MCwxMjIxMTI0NDA4LC05MjAwODc5MTksLTE4MzkxMTkwNzAsNjEzNTY2NzU2LDI4NjMzMTQwOSwyODYzMzExNTMsMjg2MzU1NzQ1LDU3MTkzNzIxNywxNDMxNjU1NzY1LDE0NDAwNDQzNzMsMzM3NTE4MTAsMTQzMTY1NTc2NSwtMTc4OTU2OTcwNywxMjI3MTM0MDI1LC0xODQwNzAwMjcwLDYxMzU2NzUyNCwxMjI3MTMzNTEzLC0xODQwNzAwMjcwLC0xNTMzOTE2ODkyLDE0MzE2NTU3NjUsMTQzMTY1NTgyOSw2LDEwOTA3OTc2MzMsMjcyNzQwMzY4LDY4MTc0MDg0LDEwOTA3ODUzNDUsMjcyNjk2MzM2LDY4MTc0MDg0LDEsLTEsMiwxNTY4MTAxNzE3LDE0MzE2NTU3NjUsLTIxMjIyMTkxMzUsLTIxMjIyMTkxMzUsLTIxMjIyMTkxMzUsLTIxMjIyMTkxMzUsLTIxMjIyMTkxMzUsMTMwXSwicGVybWlzc2lvbnNDb21wcmVzc2VkIjp0cnVlLCJpc3MiOiJBUEkiLCJqdGkiOiI1ZDlmM2U4OS1mNTc0LTQ4ZDEtYTQ0MC0zYTJmNDczMTBhZDciLCJleHAiOjE1ODYyNzE4NDQsImlhdCI6MTU4NjI0MzA0NH0.UUR2l_0WfJjP-B46K5WWPL_Tet0dCmplfw9PpVYgM_mZXRn7D-pQ_-BkCpC67jfYUu0ag8KuZSV0Uau1cIrf-w";
		String requestUrl = "https://tvs-dev-gw.operativeone.com/products/v1/8e82e7c3-1999-4987-86bc-68843e4711db/productset";
		
		APIUtils apiUtils = new APIUtils();
		
		// GET Request
		Response response = apiUtils.jsonClientGet(requestUrl, bearerToken);
		System.out.println(response.asString());
		
		// Reading Data from JSON
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.asString());
		System.out.println("ID: " + jsonNode.get(0).findValue("id"));
		System.out.println("Name: " + jsonNode.get(0).findValue("name"));
		
	}
	
	@Test(priority=1)
	public void buildJsonNode() {
//		JSONArray arr= new JSONArray();
//		JSONObject obj1 = new JSONObject();
//		obj1.put("id", "name");
//		arr.put(obj1);
//		System.out.println(arr.toString());	
//		
//		/** Output: [{"id":"name"}] **/

 /**		Expected Output: 
		[
		  {
		    "id": "yN84yNFrQli71K-BCr3xGw",
		    "name": "       SB Ancillary_update_new"
		  },
		  {
		    "id": "a_6THXS5RAKQMQUu4gPtlg",
		    "channel": [
		      "qhtD0640RYqXCL1EuidBGw"
		    ],
		    "name": "      SB Pregame Group 1 1p-3p"
		  }]
**/
		JSONArray arr1 = new JSONArray();
		JSONObject obj2 = new JSONObject();
		obj2.put("id", "yN84yNFrQli71K-BCr3xGw");
		obj2.put("name", "       SB Ancillary_update_new");
		arr1.put(obj2);
		JSONObject obj3 = new JSONObject();
		obj3.put("id", "a_6THXS5RAKQMQUu4gPtlg");
		JSONArray arr2 = new JSONArray();
		arr2.put("qhtD0640RYqXCL1EuidBGw");
		obj3.put("channel", arr2);
		obj3.put("name", "      SB Pregame Group 1 1p-3p");
		arr1.put(obj3);
		System.out.println(arr1.toString());

/**		 Actual Output:
			[{"name":"       SB Ancillary_update_new","id":"yN84yNFrQli71K-BCr3xGw"},
		{"channel":["qhtD0640RYqXCL1EuidBGw"],"name":"      SB Pregame Group 1 1p-3p","id":"a_6THXS5RAKQMQUu4gPtlg"}]
**/
		
	}
}
