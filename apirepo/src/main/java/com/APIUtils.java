package com;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

/**
 * Hello world!
 *
 */
public class APIUtils 
{
	// for get Response with String request json
		public Response jsonClientGet(String requestUrl, String authVal) {
			Response response = null;
			final RequestSpecification httpRequest = RestAssured.given();
	
			// declaring headers
			httpRequest.header("Content-Type", "application/json");
			httpRequest.header("Authorization", authVal);
			
			// adding body
//			httpRequest.body(json);
			
			try {
				// GET request
				response = httpRequest.get(requestUrl);
			} catch (final Exception e) {
				throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
			}
			return response;
		}
}
