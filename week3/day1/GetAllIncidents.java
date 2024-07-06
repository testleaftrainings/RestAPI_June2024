package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllIncidents {
	
	@Test
	public void getAllIncident() {
		/*
		 * 5 step process 
		 * 1. Requirements 
		 * 2. Endpoint 
		 * 3. Construct request - Queryparam, header, auth, requestbody
		 * 4. Send the request 
		 * 5. Validate the response
		 */
		
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "wL39b$PaJ$oN");
		Response response = RestAssured.get();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
