import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class getKey_2 {
public static String apiKey;
@Test
   public void getKey(){
        Response response =  RestAssured.given().accept(JSON)
                .when().get("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/getkey")
                .then().statusCode(200).extract().response();

    Map<String,String> key = response.as(new TypeRef<Map<String, String>>() {
    });

    System.out.println(key.get("api_key"));
    apiKey=key.get("api_key");
    }

}
