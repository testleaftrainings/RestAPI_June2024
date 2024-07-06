package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateIncidentUsingPATCH extends BaseAPISteps {
	
	@Test(dependsOnMethods = {"chaining.CreateIncidentWithReqBodyasFile.createIncident"})
	public void updateIncident() {

		// auth, queryparams, headers, requestbody
		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.queryParam("sysparm_fields", "sys_id, category, number, short_description, description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\r\n"
						+ "    \"short_description\": \"Updating it in the patch request\"\r\n"
						+ "}");

		
		response = inputRequest.when().patch(sys_id);
		response.then().log().all();
	}

}
