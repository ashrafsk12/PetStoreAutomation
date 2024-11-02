package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//created for perform Create,Read,Update,Delete requests the user API

public class UserEndPoints {

	//to create user we need some payload
	
	public static Response createUser(User payload) //this method has body that we take as some payload 
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return response;	
	
	}
	
	public static Response readUser(String userName)
	{
		 Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.get(Routes.get_url);
		return response;
		
	}
	
	public static Response updateUser(User payload,String userName)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
		.when()
			.put(Routes.update_url);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		 Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
		return response;
		
	}
	

}
