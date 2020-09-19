import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DocumentType {
    Cookies cookies;
    String id;
    List<String> attachmentStages;

    @BeforeClass
    public void init() {
        baseURI = "https://test.basqar.techno.study";
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "daulet2030@gmail.com");
        credentials.put("password", "TechnoStudy123@");

        cookies = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response().detailedCookies();
    }

    @Test
    public void createDocumentType() {
        attachmentStages = new ArrayList<>();
        attachmentStages.add("EXAMINATION");
        attachmentStages.add( "EMPLOYMENT");
        attachmentStages.add( "CERTIFICATE");

        PojoDoc pojoDoc = new PojoDoc();
        pojoDoc.setName("John");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        id = given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(200);
    }

    @Test
    public void createDocumentTypeNegative(){

        attachmentStages = new ArrayList<>();
        attachmentStages.add("EXAMINATION");
        attachmentStages.add( "EMPLOYMENT");
        attachmentStages.add( "CERTIFICATE");

        PojoDoc pojoDoc = new PojoDoc();
        pojoDoc.setName("John");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        id = given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");

        given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(400); // !!!BUG: it must be 400 but when we write 201 It is passing.
                                            // It is accepting recurrent documents.

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(200); // !!!BUG it is not deleting the created document above.


    }

    @Test
    public void editDocumentType(){
        attachmentStages = new ArrayList<>();
        attachmentStages.add("EXAMINATION");
        attachmentStages.add( "EMPLOYMENT");
        attachmentStages.add( "CERTIFICATE");

        PojoDoc pojoDoc = new PojoDoc();
        pojoDoc.setName("John");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        id = given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");

        pojoDoc.setName("John1");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(200);  // !!! BUG: This is a bug When we write 201  The Test is passing.

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(200);

    }

    @Test
    public void deleteDocumentTypePositive(){

        attachmentStages = new ArrayList<>();
        attachmentStages.add("EXAMINATION");
        attachmentStages.add( "EMPLOYMENT");
        attachmentStages.add( "CERTIFICATE");

        PojoDoc pojoDoc = new PojoDoc();
        pojoDoc.setName("John");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        id = given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(200);
    }

    @Test
    public void deleteDocumentTypeNegative(){

        attachmentStages = new ArrayList<>();
        attachmentStages.add("EXAMINATION");
        attachmentStages.add( "EMPLOYMENT");
        attachmentStages.add( "CERTIFICATE");

        PojoDoc pojoDoc = new PojoDoc();
        pojoDoc.setName("John");
        pojoDoc.setDescription("asd");
        pojoDoc.setSchoolId("5c5aa8551ad17423a4f6ef1d");
        pojoDoc.setActive(true);
        pojoDoc.setRequired(true);
        pojoDoc.setAttachmentStages(attachmentStages);
        id = given().cookies(cookies)
                .body(pojoDoc)
                .contentType(ContentType.JSON)
                .when().post("/school-service/api/attachments")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(200);

        given().cookies(cookies)
                .when().delete("/school-service/api/attachments/"+id)
                .then().statusCode(404);
        
    }
}
