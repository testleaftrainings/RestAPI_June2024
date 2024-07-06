package week4.day1;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class LearnAssertions {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";

		File inputFile = new File("./src/main/resources/CreateIncident_request.json");

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "wL39b$PaJ$oN")
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(inputFile);

		Response response = inputRequest.when().post();
		response.prettyPrint();
		System.out.println(response.statusCode());

		// Convert the response to validatable response -> apply assert on required
		// field
		ValidatableResponse validatableResponse = response.then();
		
		validatableResponse.assertThat().statusCode(Matchers.equalTo(201));
		
		validatableResponse.assertThat()
		.body("result.number", 
				Matchers.containsString("INC"));
		
		validatableResponse.assertThat()
		.body("result.short_description", 
				Matchers
				.equalTo("Created from restassured to validate hamcrest assertions"));
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
