package week3.day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentsWithMultipleQueryParam {
	
	@Test
	public void getAllIncidentWithMultipleQueryParam() {
		/*
		 * 5 step process 
		 * 1. Requirements 
		 * 2. Endpoint 
		 * 3. Construct request - Queryparam, header, auth, requestbody - Given
		 * 4. Send the request - When 
		 * 5. Validate the response - Then
		 */
		
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		
		Map<String, String> allQueryParams= new HashMap<String, String>();
		allQueryParams.put("sysparm_fields", "sys_id, category, number");
		allQueryParams.put("category", "software");
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.auth()
		.basic("admin", "wL39b$PaJ$oN")
		/*.queryParam("sysparm_fields", "sys_id, category, number")
		.queryParam("category", "software")*/
		.queryParams(allQueryParams);
		
		Response response = inputRequest.when().get();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
	}

}
