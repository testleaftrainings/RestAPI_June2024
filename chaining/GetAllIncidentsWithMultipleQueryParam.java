package chaining;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentsWithMultipleQueryParam extends BaseAPISteps {
	
	@Test
	public void getAllIncidentWithMultipleQueryParam() {
		
		Map<String, String> allQueryParams= new HashMap<String, String>();
		allQueryParams.put("sysparm_fields", "sys_id, category, number");
		allQueryParams.put("category", "software");
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.accept(ContentType.JSON)
		.queryParams(allQueryParams);
		
		response = inputRequest.when().get();
		response.then().assertThat().body("result.sys_id", Matchers.hasItem(sys_id));
	}

}
