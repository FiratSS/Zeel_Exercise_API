import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class checkAppointmentsOnJune_5 {

    @Test
    public void deserializePatients(){

        Response response =  RestAssured.given().accept(JSON)
                .when().get("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/patient")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        int count=0;


        for (Map patient: parsedResponse ) {

          String date = (String) patient.get("appointment_date");
          if (date.substring(5,7).equals("06")){

                count++;
                System.out.println(patient.get("appointment_date"));
            }
        }

        System.out.println("Total appointments on june = "+count);
        Assert.assertTrue(count>=1);
    }
}
