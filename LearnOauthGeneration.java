package week6.day2;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LearnOauthGeneration {
	
	String bearerToken;
	
	@BeforeMethod
	public void generateBearerToken() {
		RestAssured.baseURI = "https://dev196137.service-now.com/";
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.log()
		.all()
		.formParam("grant_type", "password")
		.formParam("client_id", "75425ac9516b82107c6665e831e2d0f5")
		.formParam("client_secret", "&m|MMNr9Iw")
		.formParam("username", "admin")
		.formParam("password", "wL39b$PaJ$oN")
		.contentType("application/x-www-form-urlencoded");
		
		Response response = inputRequest.when().post("oauth_token");
		
		response.statusCode();
		response.prettyPrint();
		
		bearerToken = response.body().jsonPath().getString("access_token");
		System.out.println("bearerToken: "+bearerToken);
	}
	
	@Test
	public void createIncident() {
		File inputFile = new File("./src/main/resources/CreateIncident_request.json");

		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.auth()
				.oauth2(bearerToken)
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.XML)
				.body(inputFile);
		
		Response response = inputRequest.when().post("/api/now/table/incident");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		//String sys_id = response.body().jsonPath().get("result.sys_id");
		String sys_id = response.body().xmlPath().get("response.result.sys_id");
		System.out.println("Sys_id retrived from the response: "+sys_id);
		
	}

}
