package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTest implements TestHelper {

    @Test
    public void addNewContactPositive() throws IOException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"desc");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+ADD_CONTACT)
                .addHeader(
                        AUTHORIZATION_HEADER,
                        PropertiesReaderXML.getProperty("token",TestHelper.XML_FILE_PATH))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("Message : "+contactResponseModel.getMessage());
       String id = IDExtractor.getId(contactResponseModel.getMessage());
       Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void addNewContactNegative() throws IOException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "111",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"desc");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact),JSON);

        Request request = new Request.Builder()
                .url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER,
                        PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .post(requestBody)
                .build();

        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getMessage());
        Assert.assertEquals(response.code(), 400);
    }

}
