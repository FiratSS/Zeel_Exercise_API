import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class explodeIDs_7 {

    @Test
    public void explodeId(){
        Response response =  RestAssured.given().accept(JSON)
                .when().get("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/patient")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });


        for (Map patient: parsedResponse ) {

            String patientID = (String) patient.get("id");

            String birthdate = (String) patient.get("birthdate");
            String app_date = (String) patient.get("appointment_date");
            Map<String,String> name= (Map<String, String>) patient.get("name");

            String ID= name.get("firstName").substring(0,1)+name.get("lastName").substring(0,1)
                    +birthdate.replaceAll("-","")+app_date.replaceAll("-","");

            System.out.println(ID);
            System.out.println(patientID);


            Assert.assertTrue(patientID.substring(18).chars().allMatch(Character::isDigit));
            Assert.assertEquals(patientID.substring(0,18),ID);
        }
    }
}
