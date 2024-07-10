package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTests implements TestHelper {
    String id;
    Contact contact;

    @Test
    public void updateContactPositive(){
        RestAssured.baseURI= BASE_URL+UPDATE_CONTACTS;
        contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(4,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"test description");

       String message = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token",XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON).when()
                .post().then().assertThat().statusCode(200).extract().path("message");
               id = IDExtractor.getId(message);

               contact.setId(id);
               contact.setEmail(EmailGenerator.generateEmail(3,3,3));

              given()
                       .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token",XML_FILE_PATH))
                       .body(contact).contentType(ContentType.JSON)
                       .when().put().then()
                       .assertThat()
                       .body("message", containsString("updated"));


    }
}
