package resourcse;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification Requestspecification() throws IOException
	{
		if(req==null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("logs.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalData("baseURI"))
					.addQueryParam("key", "qaclick123")
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		
		return req;
	}
	
	
	public static String getGlobalData(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis =new FileInputStream("E:\\Automation_Practice\\APIFrameworkBDD\\src\\test\\java\\resourcse\\globalData.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	public static String GetKeyValue(Response response,String key)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
