package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateIncidentUsingPATCH {
	
	@Test
	public void updateIncident() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident/";

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "wL39b$PaJ$oN")
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\r\n"
						+ "    \"short_description\": \"Updating it in the patch request\"\r\n"
						+ "}");

		
		Response response = inputRequest.when().patch("2a2fdce6470382109555f884116d437a");

		response.prettyPrint();
		System.out.println(response.statusCode());
		
	}

}
