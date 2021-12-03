package resourcse;

import java.util.Arrays;
import java.util.List;

import POJO.addPlace;
import POJO.location;

public class TestData {
	
	public addPlace AddPlacePayload(String name,String address,String language)
	{
		addPlace a=new addPlace();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setName(name);
		a.setPhone_number("88800000");
		a.setLanguage(language);
		a.setWebsite("www.hari.com");
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		List<String> messages = Arrays.asList("type1", "type2");
		a.setTypes(messages);
		return a;
	}
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}
}
