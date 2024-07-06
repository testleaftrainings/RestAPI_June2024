package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentWithReqBody {

	@Test
	public void createIncident() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "wL39b$PaJ$oN")
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\r\n" + "    \"short_description\": \"Created from restassured\",\r\n"
						+ "    \"description\": \"Sending the request body as string\"\r\n" + "}");

		
		Response response = inputRequest.when().post();

		response.prettyPrint();
		System.out.println(response.statusCode());

	}

}
