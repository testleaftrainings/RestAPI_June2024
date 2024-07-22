package week6.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LearnCookies {
	
	public String session_id;
	
	@Test
	//Send the first request, with proper authentication and from response store the cookie in a string variable
	public void extractCookie() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		RequestSpecification inputRequest = RestAssured
											.given()
											.auth()
											.basic("admin", "wL39b$PaJ$oN")
											.queryParam("sysparm_fields", "sys_id, category, number")
											.contentType(ContentType.JSON)
											.accept(ContentType.JSON);
			
		Response response = inputRequest.when().post();
		session_id = response.getCookie("JSESSIONID");
		System.out.println("Cookie: "+session_id);
		//response.then().log().all();
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test(dependsOnMethods = "extractCookie")
	public void applyCookie() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.cookie("JSESSIONID", session_id)
				.queryParam("sysparm_fields", "sys_id, category, number")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON);
		Response response = inputRequest.when().get();
		System.out.println(response.statusCode());
	}

}
