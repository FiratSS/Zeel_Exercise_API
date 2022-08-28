import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class checkUpdatedInfo_10 {

    @Test
    public void checkInfo(){

        Response response =  RestAssured.given().accept(JSON)
                .when().get("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/patient")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (Map patient :parsedResponse ) {

            if (patient.get("id").equals("SR19760827202206208364")){

                String birthdate = (String) patient.get("birthdate");
                String app_date = (String) patient.get("appointment_date");
                Map<String,String> name= (Map<String, String>) patient.get("name");
                Map<String,String> address= (Map<String, String>) patient.get("address");

                String ID= "SR" +birthdate.replaceAll("-","")+app_date.replaceAll("-","");

                String patientID = (String) patient.get("id");
                Assert.assertEquals(patientID.substring(0,18),ID);


                Assert.assertEquals("Tester",name.get("firstName"));
                Assert.assertEquals( "Awesome",name.get("lastName"));
                Assert.assertEquals("221B Baker st", address.get("street"));
                Assert.assertEquals("London",address.get("city"));
                Assert.assertEquals("London",address.get("state"));
                Assert.assertEquals("00001",address.get("zip"));



            }
        }
    }
}
