package chaining;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BaseAPISteps {
	
	public static String sys_id;
	public Response response;
	
	@BeforeTest
	public void preSteps() {
		RestAssured.baseURI = "https://dev196137.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "wL39b$PaJ$oN");
	}
	
	@AfterMethod
	public void postSteps() {
		response.then().log().all();
	}

}
