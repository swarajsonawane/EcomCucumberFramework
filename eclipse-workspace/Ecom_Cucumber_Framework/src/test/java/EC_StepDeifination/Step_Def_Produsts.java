package EC_StepDeifination;

import Common_Resources.EC_RequestSpecification;

import Common_Resources.EC_Resources;

import Pojo_Classes.LoginCredential;
import Pojo_Classes.getOrders;
import Pojo_Classes.orders;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Step_Def_Produsts extends EC_RequestSpecification {
	
	public static RequestSpecification stepRequest;
	public static Response stepResponse;
	public static String accessToken;
	public static String userID;
	public static LinkedHashMap<String, String> productID;
	
	@Given("Login Functionality Payload with {string} and {string}")
	public void login_functionality_payload_with_and(String Email, String Password) {
		
		LoginCredential LC=new LoginCredential();
		
		LC.setUserEmail(readProperties(Email));
		LC.setUserPassword(readProperties(Password));
 stepRequest=given().spec(basicRequestSpecification()).contentType(ContentType.JSON).body(LC);
		
		}

	@When("user call {string} using {string} http request")
	public void user_call_using_http_request(String resourses, String method) {
 EC_Resources rs=EC_Resources.valueOf(resourses);
	   if(method.equalsIgnoreCase("post")) {
	stepResponse=stepRequest.when().post(rs.getResourses()).then().
			spec(basicResponseSpecification()).extract().response();
	   }else if(method.equalsIgnoreCase("get")) {
		   stepResponse=stepRequest.when().get(rs.getResourses()).then().
					spec(basicResponseSpecification()).extract().response();
	   }else if(method.equalsIgnoreCase("delete")) {
		   stepResponse=stepRequest.when().delete(rs.getResourses()).then().
					spec(basicResponseSpecification()).extract().response();
	   }else {
		   stepResponse=stepRequest.when().put(rs.getResourses()).then().
					spec(basicResponseSpecification()).extract().response();
	   }
	}

	@Then("LoginAPI called got successfully with status code {int}")
	public void login_api_called_got_successfully_with_status_code(int statuscode) {
	assertEquals(stepResponse.getStatusCode(),statuscode);
	accessToken=getActualResponse(stepResponse,"token");
	 userID=getActualResponse(stepResponse,"userId");
		}

	@Then("Verify {string} in response body is {string}")
	public void verify_in_response_body_is(String key, String value) {
	    
	assertEquals(getActualResponse(stepResponse,key), value);
		
	
	}
	
	@Given("addProduct Payload with {string},{string},{string},{string},{string},{string} and {string}")
	public void add_product_payload_with_and(String name, String category, String subcategory, String price, String descri, String productfor,String imagepath) {
	
		
		stepRequest= given().spec(basicRequestSpecification()).header("Authorization",accessToken).
		param("productName", name).
	    param("productCategory", category).
	    param("productSubCategory", subcategory).
	    param("productPrice", price).
	    param("productDescription", descri).
	    param("productFor",productfor ).
	    param("productAddedBy", userID).
	    multiPart("productImage",new File(imagepath));
	
	}

	@Then("CreateProductAPI called got successfully with status code {int}")
	public void create_product_api_called_got_successfully_with_status_code(int statuscode) {
	    assertEquals(stepResponse.getStatusCode(),statuscode);
	}
	
	@Then("Stored {string} by using {string}")
	public void stored_by_using(String productId, String productName) {
	   productID=new LinkedHashMap<String, String>();
	    productID.put(productName, getActualResponse(stepResponse,productId));
	    System.out.println(productID.get(productName));
	    setPropertiesValue(productName, productID.get(productName).toString());
	   
	}
	
//	@Given("deleteProduct payload with productId consist with {string}")
//	public void delete_product_payload_with_product_id_consist_with(String Names) {
//		
//		stepRequest	=given().spec(basicRequestSpecification()).
//				   contentType(ContentType.JSON).
//				   header("Authorization",accessToken).
//				   pathParams("productIdKey", readProperties(Names));
//		
//	}
//
//	@Then("DeleteProductAPI called got successfully with status code {int}")
//	public void delete_product_api_called_got_successfully_with_status_code(int statuscode) {
//		assertEquals(stepResponse.getStatusCode(),statuscode);
//	}

	@Given("placeOrderProduct payload with {string} and productId consist with {string}")
	public void place_order_product_payload_with_and_product_id_consist_with(String country, String productNames) {
		orders or=new orders();
		or.setCountry(country);
		or.setProductOrderedId(readProperties(productNames));
		
		List<orders> listOfOrder=new ArrayList<orders>();
		listOfOrder.add(or);
		
		getOrders getorder=new getOrders();
		getorder.setOrders(listOfOrder);
		
		stepRequest	=given().spec(basicRequestSpecification()).
				   contentType(ContentType.JSON).
				   header("Authorization",accessToken).body(getorder);
	}

	@Then("placeOrderAPI called got successfully with status code {int}")
	public void place_order_api_called_got_successfully_with_status_code(int statuscode) {
		assertEquals(stepResponse.getStatusCode(),statuscode);
	}
	
	
}
