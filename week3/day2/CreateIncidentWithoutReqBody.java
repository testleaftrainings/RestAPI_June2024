package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentWithoutReqBody {
	
	@Test
	public void createIncident() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		
		RequestSpecification inputRequest = RestAssured
											.given()
											.auth()
											.basic("admin", "wL39b$PaJ$oN")
											.queryParam("sysparm_fields", "sys_id, category, number")
											.contentType(ContentType.JSON)
											.accept(ContentType.XML);
		
		
		Response response = inputRequest.when().post();
		
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
