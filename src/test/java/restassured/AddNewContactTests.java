package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactTests implements TestHelper {
    @Test
    public void addNewContact(){
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(4,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"test description");
        given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty
                        ("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post(BASE_URL+ADD_CONTACT)
                .then().assertThat().statusCode(200);
    }

}
