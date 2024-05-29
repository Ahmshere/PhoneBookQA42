package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.Contact;
import models.ContactResponseModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactTests implements TestHelper {

    private Contact createContact(){
       return new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(4,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"test description");
    }
    private Contact createIncorrectContact(){
        return new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(4,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"test description");
    }

    @Test
    public void addNewContact(){

        given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty
                        ("token", XML_FILE_PATH))
                .body(createContact())
                .contentType(ContentType.JSON)
                .when().post(BASE_URL+ADD_CONTACT)
                .then().assertThat().statusCode(200);
    }


}
