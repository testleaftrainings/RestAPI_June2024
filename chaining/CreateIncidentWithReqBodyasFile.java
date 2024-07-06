package chaining;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentWithReqBodyasFile extends BaseAPISteps {
	
	@Test
	public void createIncident() {	
		File inputFile = new File("./src/main/resources/CreateIncident_request.json");
		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(inputFile);
		
		response = inputRequest.when().post();
		sys_id = response.body().jsonPath().get("result.sys_id");
		//sys_id = response.body().xmlPath().get("response.result.sys_id");
		System.out.println("Sys_id retrived from the response: "+sys_id);
		
		response.then().assertThat().statusCode(Matchers.equalTo(201));
		
		response.then().assertThat().body("result.short_description", 
				Matchers.equalTo("Created from restassured to validate hamcrest assertions"));
		
		
	}

}
