package restassured;

import helpers.Logger;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactListModel;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class GetAllContactsTest implements TestHelper {

    @Test
    public void getAllContactsPositive() throws IOException {
        Logger.setupLogger("src/logs/testresult2.log");
        ContactListModel contactList = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token",XML_FILE_PATH))
                .when()
                .get(BASE_URL+GET_ALL_CONTACTS)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ContactListModel.class);
        for (Contact contact : contactList.getContacts()){
            System.out.println(contact.getName());
            System.out.println(contact.getEmail());
            System.out.println("**************************************************");
        }
       Logger.closeLogger();
        System.out.println("++++++++++++++");

    }

}
