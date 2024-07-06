package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIncident {
	
	@Test
	public void deleteIncident() {
		
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident/";

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "wL39b$PaJ$oN");
		
		Response response = inputRequest.when().delete("2a2fdce6470382109555f884116d437a");

		System.out.println(response.statusCode());
		
	}

}
