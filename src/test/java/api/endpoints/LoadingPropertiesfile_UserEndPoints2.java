package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//created for perform Create,Read,Update,Delete requests the user API

public class LoadingPropertiesfile_UserEndPoints2 {
	
		//this method is created for getting URL'S from properties file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load Properties file //routes=name of property file
		return routes;
	}

	//to create user we need some payload
	
	public static Response createUser(User payload) //this method has body that we take as some payload 
	{
		String PostURL=getUrl().getString("post_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(PostURL);
		
		return response;	
	
	}
	
	public static Response readUser(String userName)
	{
		String GetURL=getUrl().getString("get_url");
		 Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.get(GetURL);
		return response;
		
	}
	
	public static Response updateUser(User payload,String userName)
	{
		String UpdateURL=getUrl().getString("update_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
		.when()
			.put(UpdateURL);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
			String DeleteURL=getUrl().getString("delete_url");
		 Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
		
		.when()
			.delete(DeleteURL);
		return response;
		
	}
	

}
