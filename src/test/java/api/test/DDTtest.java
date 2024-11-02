package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTtest {
	
	@Test(priority=1,dataProvider= "Data",dataProviderClass=DataProviders.class)
	public void test_PostUser(String userID,String UserName,String fname,String lname,String useremail,String pwd,String phno)
	{
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phno);
		
		Response response=UserEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void test_DeleteUserByName(String UserName)
	{
		Response response=UserEndPoints.deleteUser(UserName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
