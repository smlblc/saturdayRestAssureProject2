import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Notification {
    Cookies cookies;
    String id;

    @BeforeClass
    public void init() {
        baseURI = "https://test.basqar.techno.study";
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("username", "daulet2030@gmail.com");
        bodyMap.put("password", "TechnoStudy123@");

        cookies = given()
                .body(bodyMap)
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response().detailedCookies();
    }

    @Test
    public void createNotification() {
        Pojo pojo = new Pojo();
        pojo.setName("John");
        pojo.setDescription("elementary");
        pojo.setType("STUDENT_PAYMENT_TIME");
        pojo.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        id = given().cookies(cookies)
                .body(pojo)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/notifications")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "createNotification")
    public void createNotificationNegative() {
        Pojo pojo = new Pojo();
        pojo.setName("John");
        pojo.setDescription("elementary");
        pojo.setType("STUDENT_PAYMENT_TIME");
        pojo.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        given().cookies(cookies)
                .body(pojo)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/notifications")
                .then().statusCode(400);
    }

    @Test(dependsOnMethods = "createNotificationNegative")
    public void editNotification() {
        Pojo pojo = new Pojo();
        pojo.setName("John1");
        pojo.setDescription("elementary");
        pojo.setType("STUDENT_PAYMENT_TIME");
        pojo.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojo.setId(id);
        given().cookies(cookies)
                .body(pojo)
                .contentType(ContentType.JSON)
                .when().put("/school-service/api/notifications")
                .then().statusCode(200);
    }

    @Test (dependsOnMethods = "editNotification")
    public void deleteNotification() {
        given().cookies(cookies).when().delete("/school-service/api/notifications/" + id).then().statusCode(200);
    }

    @Test (dependsOnMethods = "deleteNotification")
    public void deleteNotificationNegative() {
        given().cookies(cookies).when().delete("/school-service/api/notifications/" + id).then().statusCode(404);
    }

}
