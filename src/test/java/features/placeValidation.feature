Feature: Place API Validation
@addplace
Scenario Outline: Verify the Place is successfully added[AddPlaceAPI]
	Given Add Place Payload with "<name>" "<address>" "<language>"
	When user calls "addPlaceAPI" with "POST" request
	Then Verify the API call got success with status code 200
	And Verify "status" in response body is "OK"
	And Verify "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name|address|language|
	|NewPlace_2|newyork_2|English_2|
	|NewPlace|newyork|English|
	
@deleteplace	
Scenario: Verify if delete place functionality is working
	
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" request
	Then Verify the API call got success with status code 200
	And Verify "status" in response body is "OK"