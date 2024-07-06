package week3.day2;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentWithReqBodyasFile {
	
	@Test
	public void createIncident() {
		
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		
		File inputFile = new File("./src/main/resources/CreateIncident_request.json");

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "wL39b$PaJ$oN")
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.XML)
				.body(inputFile);
		
		Response response = inputRequest.when().post();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		//String sys_id = response.body().jsonPath().get("result.sys_id");
		String sys_id = response.body().xmlPath().get("response.result.sys_id");
		System.out.println("Sys_id retrived from the response: "+sys_id);
		
	}

}
