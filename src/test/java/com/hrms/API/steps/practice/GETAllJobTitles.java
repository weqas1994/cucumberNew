package com.hrms.API.steps.practice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class GETAllJobTitles {

	private Response response;
	private RequestSpecification request;

	@Given("user calls jobTitle API")
	public void user_calls_jobTitle_API() {

		request = given().header("Content-Type", "application/json").header("Authorization",
				SyntaxAPIAuthenticationSteps.Token);
		System.out.println(request.log().all());

	}

	@When("User retrieves response for \\/jobTitle.php")
	public void user_retrieves_response_for_jobTitle_php() {

		response = request.when().get("http://18.232.148.34/syntaxapi/api/jobTitle.php");

		response.prettyPrint();

	}

	@Then("status code is {int} for jobTitle")
	public void status_code_is_for_jobTitle(Integer int1) {

		response.then().assertThat().statusCode(int1);
	}

	@Then("user validates job titles")
	public void user_validates_job_titles() {

		String variable = response.jsonPath().getString("Job.Title.List[0]");
		System.out.println(variable);
		JsonPath jsonPathGrabber = response.jsonPath();
		// List<String> allJobTitless = response.jsonPath().getList("Job Title
		// List[0]");
		// System.out.println(allJobTitless.size());

		JSONObject json = new JSONObject(response.prettyPrint());
		JSONArray array = json.getJSONArray("Job Title List");
		for (int i = 0; i <= array.length() - 1; i++) {
			System.out.println(array.get(i));
			if (i == 0) {
				Assert.assertEquals("Cloud Architect", array.get(i));
			}else if(i==1) {
				Assert.assertEquals("Cloud Consultant", array.get(i));
			}else if(i==2) {
				Assert.assertEquals("Cloud Product and Project Manager", array.get(i));
			}else if(i==3) {
				Assert.assertEquals("IT Analyst", array.get(i));
			}else if(i==4) {
				Assert.assertEquals("Network Administrator", array.get(i));
			}else if(i==5) {
				Assert.assertEquals("IT Support Manager", array.get(i));
			}else if(i==6) {
				Assert.assertEquals("Data Quality Manager", array.get(i));
			}else if(i==7) {
				Assert.assertEquals("Database Administrator", array.get(i));
			}
		}
		Assert.assertEquals(23, array.length());
		System.out.println(array.length());

	}

}
