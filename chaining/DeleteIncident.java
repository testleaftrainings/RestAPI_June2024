package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIncident extends BaseAPISteps {
	
	@Test(dependsOnMethods = {"chaining.UpdateIncidentUsingPATCH.updateIncident"})
	public void deleteIncident() {
		
		response = RestAssured.delete(sys_id);
		response.then().log().all();
		
	}

}
