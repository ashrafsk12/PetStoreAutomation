package api.endpoints;

/*
 Swagger URI --> https://petstore.swagger.io

Create user(Post): https://petstore.swagger.io/v2/user

Get user (Get): https://petstore.swagger.io/v2/user/{username} 

Update user (Put): https://petstore.swagger.io/v2/user/{username}

Delete user (Delete): https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {
	
	//User Module
	
	public static String baseurl="https://petstore.swagger.io/v2";
	
	public static String post_url=baseurl+"/user";
	public static String get_url=baseurl+"/user/{username}";
	public static String update_url=baseurl+"/user/{username}";
	public static String delete_url=baseurl+"/user/{username}";
	
	//similarly Store Module
	//Here we create Store module Urls
	
	//Pet Module
		//Here we create pet module Urls
}
