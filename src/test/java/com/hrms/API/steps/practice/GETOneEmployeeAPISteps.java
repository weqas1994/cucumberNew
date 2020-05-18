package com.hrms.API.steps.practice;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import com.hrms.utils.APIConstants;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class GETOneEmployeeAPISteps {

	private static RequestSpecification request;
	public Response response;

	@Given("user calls getOneEmployee API")
	public void user_calls_getOneEmployee_API() {
		request = given().header("Content-Type", "application/json")
				.header("Authorization", SyntaxAPIAuthenticationSteps.Token)

				.param("employee_id", POSTCreateEmployeeAPI.employee_ID);
		// request.queryParam("employee_id", CreateEmployeeAPI.employee_ID);

	}

	@When("User retrieves response for getOneEmployeeAPI")
	public void user_retrieves_response_for_getOneEmployeeAPI() {

		response = request.when().get(APIConstants.GET_EMPLOYEE_URI);
		response.prettyPrint();

	}

	@Then("status code is {int} for getOneEmployeeAPI")
	public void status_code_is_for_getOneEmployeeAPI(int int1) {

		Assert.assertEquals(int1, response.getStatusCode());
		response.then().assertThat().statusCode(int1);

	}

	@Then("user validates created employee exists")
	public void user_validates_created_employee_exists() {

		response.then().body("employee[0].employee_id", equalTo(POSTCreateEmployeeAPI.employee_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		String actualEmployeeID = jsonPathEvaluator.get("employee[0].employee_id");

		try {
			Assert.assertEquals("Verifying Employee ID's Match", POSTCreateEmployeeAPI.employee_ID, actualEmployeeID);

		} catch (AssertionError e) {
			System.out.println("Employee ID's DO NOT MATCH");
		}
		System.out.println("Employee ID's Match");

		// Assert.assertEquals("Verifying Employee ID's Match", actualEmployeeID,
		// CreateEmployeeAPI.employee_ID);

	}

}
