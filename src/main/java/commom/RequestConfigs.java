package commom;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestConfigs {

	String url="http://test-game.rr.tv/user";

	public Response  setPost(Map map,String url1)
	{

		return RestAssured.given().params(map).when().post(url+url1) ;
	}

	public Response  setGet()
	{
		return RestAssured.given().when().get(url) ;

	}
}
