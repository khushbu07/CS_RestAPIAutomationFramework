package api.endPoints;

import static io.restassured.RestAssured.given;

import org.apache.poi.sl.usermodel.ObjectMetaData.Application;

import api.payLoads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {

	public static Response createUser(User payload) {

		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(Routes.post_url);

		return response;
	}

	public static Response getUser(String userName) {

		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", userName)

				.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String userName, User payload) {

		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(Routes.put_url);

		return response;
	}

	public static Response deleteUser(String userName) {

		Response response = given().accept(ContentType.JSON)
				.pathParam("username", userName)

				.when().delete(Routes.del_url);

		return response;
	}

}
