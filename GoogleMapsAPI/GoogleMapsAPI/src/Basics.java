import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static files.ReUsableMethods.rawToJson;
import static io.restassured.RestAssured.*;
import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
	
				RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=  given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", "APP")
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(); 
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		
		String newAddress = "Hebbal";
		
		 given()).log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equals("Address successfully updated"));
		
		
		
	String Resp=given().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js1= rawToJson(resp);
	String actualAddress =js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, newAddress);
	
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

	private static Object given() {
		// TODO Auto-generated method stub
		return null;
	}

}
