package week3.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentsWithQueryParam {
	
	@Test
	public void getAllIncidentWithQueryParam() {
		/*
		 * 5 step process 
		 * 1. Requirements 
		 * 2. Endpoint 
		 * 3. Construct request - Queryparam, header, auth, requestbody - Given
		 * 4. Send the request - When 
		 * 5. Validate the response - Then
		 */
		
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.accept(ContentType.JSON)
		.auth()
		.basic("admin", "wL39b$PaJ$oN")
		.queryParam("sysparm_fields", "sys_id, category, number")
		.queryParam("category", "software");
		
		Response response = inputRequest.when().get();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		
		//INC0000003
		response.then().assertThat()
		.body("result.number", 
				Matchers.hasItem("INC0000054"));
		
	}

}
