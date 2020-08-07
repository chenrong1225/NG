package testN.NG;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import commom.Http;
import commom.RSAUtil;
import commom.RequestConfigs;
import io.restassured.response.Response;

@Test
public class TestDemo {

    String url="http://test-game.rr.tv/user/login";
	String sing;
	RSAUtil rs=new RSAUtil();
	Http h=new Http();
	HashMap<String, Object> map=new HashMap<String, Object>();

  @Test
  public void f() throws Exception {

	  String request = "";
	  map.put("phone_mob","17721228634");
	  map.put("password","ad4ca7940534797bc1bfe161ac3e8961");
	  map.put("time_stamp","1556174768337"/*new Date().getTime()*/);
	  map.put("deviceId", "866716033082425");
	  map.put("channel_id", "12");
	  map.put("game_id", "1128");
	  String test=rs.contest(map);
	  sing=rs.signWithRSA("channel_id=12&deviceId=866716033082425&game_id=1128&password=ad4ca7940534797bc1bfe161ac3e8961&phone_mob=17721228634&time_stamp=1556175172250");
	  map.put("sign", sing);
	  System.out.println(sing);
	  String json=h.doPost(url, map);
	  System.out.println(json);

  }
}
