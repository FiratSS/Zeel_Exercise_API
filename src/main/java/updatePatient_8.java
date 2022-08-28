import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;



public class updatePatient_8 {

    @Test
    public void patchPatient(){


        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .queryParam("api_key","940893157622")
                .body(payload.getPayload())
                .when().patch("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/update")
                .then().statusCode(200)
                .extract().response();
    }
}
