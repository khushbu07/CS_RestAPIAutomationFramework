package api.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.userEndPoints;
import api.payLoads.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//userPayload.setUserStatus(faker.s);
		
		logger=LogManager.getLogger("RestAssuredFramework");
		
	}
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response=userEndPoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200,"Validation for Status code");
		
		//log
		logger.info("create user executed");
	}

	@Test(priority=2)
	public void testGetUser() {
		Response response=userEndPoints.getUser(this.userPayload.getUsername());
		System.out.println("========Read Get data===========");
		//log response
		response.then().log().all();
		
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200,"Validation for Status code");
		//log
		logger.info("get user executed");
		
	}
	@Test(priority=3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response=userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		System.out.println("========Read update data===========");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200,"Validation for Status code");
		
		//chk first name is updated or not
		Response responsePostUpdate=userEndPoints.getUser(this.userPayload.getUsername());
		System.out.println("chk updated data");
		responsePostUpdate.then().log().all();
		
		//log
		logger.info("Update user executed");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response=userEndPoints.deleteUser(userPayload.getUsername());
		System.out.println("========Read delete data===========");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200,"Validation for Status code");
		
		//log
		logger.info("Delete user executed");
		
	}
	
	
}
