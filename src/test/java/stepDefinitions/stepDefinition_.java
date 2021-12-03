package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import POJO.addPlace;
import POJO.getPlace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourcse.*;

public class stepDefinition_ extends Utils {

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String place_id;
	TestData td = new TestData();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload(String name, String address, String language) throws IOException {

		addPlace a = td.AddPlacePayload(name, address, language);
		res = given().spec(Requestspecification()).body(a);

	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_http_request(String resource, String Httpreq) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (Httpreq.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (Httpreq.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

	}

	@Then("Verify the API call got success with status code {int}")
	public void verify_the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("Verify {string} in response body is {string}")
	public void verify_Status_in_response_body_is_ok(String s1, String s2) {

		getPlace gp = response.as(getPlace.class);
		
		
		if (s1.equals("status"))
		{
			String st = gp.getStatus();
			assertEquals(st, s2);
		}
		else
		{
			String sc = gp.getScope();
			assertEquals(sc, s2);

		}
			
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String key, String resource) throws IOException {
		place_id=GetKeyValue(response,"place_id");
		res=given().spec(Requestspecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualKey=GetKeyValue(response,"name");
		assertEquals(actualKey,key);

	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(Requestspecification()).body(td.deletePlacePayload(place_id));
				
	}

}
