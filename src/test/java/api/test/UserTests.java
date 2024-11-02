package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker fake;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		fake=new Faker();
		userPayload=new User();
		
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		userPayload.setPassword(fake.internet().password(5,10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}
		
		@Test(priority=1)
		public void testPOSTUser()
		{
			logger.info("***UserTests is Started***");
			logger.info("**********Creating User************");
			Response response=UserEndPoints.createUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("*******User is Created*********");
		}
		
		@Test(priority=2)
		public void testGetUserByName()
		{
			logger.info("**********Reading User Info************");
			Response response=UserEndPoints.readUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.statusCode(), 200);
			logger.info("*********User Info is Displayed************");
		}
		
		@Test(priority=3)
		public void testUpdateUserByName()
		{
			logger.info("**********Updating User************");
			//update data using payload
			userPayload.setFirstName(fake.name().firstName());
			userPayload.setLastName(fake.name().lastName());
			userPayload.setEmail(fake.internet().safeEmailAddress());
			
			Response response=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("**********User Updated************");
			
			//checking the data after response
			
			Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
		}
		
		@Test(priority=4)
		public void testDeleteUserByName()
		{
			logger.info("**********Deleting User************");
			Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			logger.info("********User Deleted ************");
			logger.info("***UserTests is Finshed***");
		}
		
		
	

}
