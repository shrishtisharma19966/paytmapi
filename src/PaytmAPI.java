import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PaytmAPI {
	public static void main(String[] args) throws ParseException {
		RestAssured.baseURI="https://apiproxy.paytm.com";
		Response res = given().
		when().
		get("v2/movies/upcoming").
		then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		JsonPath js = new JsonPath(responseString);
		
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		List list = js.get("upcomingMovieData");
		Set<String> set = new HashSet<String>();
		
		for(int i = 0 ; i < list.size() ; i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			if(map.containsKey("releaseDate") && map.get("releaseDate") != null){
				date = sdfo.parse(map.get("releaseDate").toString());
				Assert.isTrue(date.compareTo(new Date() ) >=  0);
			}
			Assert.isTrue(map.get("moviePosterUrl").toString().contains(".jpg"), "URL contains JPG");
			String paytmMovieCode = map.get("paytmMovieCode").toString();
			if(set.contains(paytmMovieCode)) {
				System.out.println("PayTM code is not Unique");
			}
			else {
				set.add(paytmMovieCode);
			}
		}
		
	}
}
