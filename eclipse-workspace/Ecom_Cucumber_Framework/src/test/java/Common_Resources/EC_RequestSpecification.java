package Common_Resources;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class EC_RequestSpecification {
	
	public static RequestSpecification basicRequest;
	public static ResponseSpecification basicResponse;
	public static Properties prop;
	public static PrintStream logs;
	public static FileOutputStream os;
	
	public static String readProperties(String key) {
		prop=new Properties();
		try {
			prop.load(new FileInputStream(".\\src\\test\\java\\Common_Resources\\Config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return prop.getProperty(key);
		
	}
	
	public static void setPropertiesValue(String Keys,String Values) {
		try {
			prop.load(new FileInputStream(".\\src\\test\\java\\Common_Resources\\Config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
		os=new FileOutputStream(new File(".\\src\\test\\java\\Common_Resources\\Config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prop.setProperty(Keys, Values);
		
		try {
			prop.store(os, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public RequestSpecification basicRequestSpecification() {
		if(basicRequest==null) {
		try {
			logs=new PrintStream(new FileOutputStream("AllLogs.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
basicRequest=new RequestSpecBuilder().setBaseUri(readProperties("BaseURI")).
     addFilter(RequestLoggingFilter.logRequestTo(logs)).
     addFilter(ResponseLoggingFilter.logResponseTo(logs)).build();
		
		return basicRequest;
		}
		return basicRequest;
	}
	
	public ResponseSpecification basicResponseSpecification() {
basicResponse=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return basicResponse;
	}
	
	public String getActualResponse(Response response,String key) {
		String actualResponse=response.asString();
		JsonPath js=new JsonPath(actualResponse);
	return	js.getString(key);
		
	}
	
	
}
