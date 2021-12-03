package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void BeforeScenario() throws IOException 
	{
		
		stepDefinition_ st=new stepDefinition_();
		if(stepDefinition_.place_id==null)
		{
			st.add_Place_Payload("prasad", "chennai", "tamil");
			st.user_calls_with_http_request("addPlaceAPI", "POST");
			st.verify_place_id_created_maps_to_using("prasad", "getPlaceAPI");
			
		}
		
	}

}
