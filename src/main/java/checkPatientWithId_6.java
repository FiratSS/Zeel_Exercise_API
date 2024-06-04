import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class checkPatientWithId_6 {

    @Test
    public void deserializePatient(){
        Response response =  RestAssured.given().accept(JSON)
                .when().get("https://hs4hqu0udj.execute-api.us-east-1.amazonaws.com/test/patient")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        boolean bln = false;

        for (Map patient: parsedResponse ) {
            if (patient.get("id").equals("SR19760827202206208364")){
                bln=true;
                System.out.println(patient);
            }
        }

        Assert.assertTrue("patient found",bln);


    }
}
