package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateContactTest implements TestHelper {

    String idResult;
    @Test
    public void updateContactPositive() throws IOException {
        Contact contactModel = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(7,5,3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),"mydesc");

        RequestBody requestBody=RequestBody.create(GSON.toJson(contactModel),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .post(requestBody).build();

        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        String responseMsg = contactResponseModel.getMessage();
        System.out.println("RESPONSE MSG: "+responseMsg);
        idResult = IDExtractor.getId(responseMsg);

        Contact createdModel = new Contact(
                idResult,
                contactModel.getName(),
                contactModel.getLastName(),
                contactModel.getEmail(),
                contactModel.getPhone(),
                contactModel.getAddress(),
                contactModel.getDescription() );
        RequestBody updaterequestBody=RequestBody.create(GSON.toJson(createdModel),JSON);
        Request updaterequest = new Request.Builder()
                .url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .put(updaterequestBody).build();

        Response updateresponse = CLIENT.newCall(updaterequest).execute();
        ContactResponseModel contactResponseModel1 =
                GSON.fromJson(updateresponse.body().string(), ContactResponseModel.class);
        System.out.println(contactResponseModel1.getMessage());
        Assert.assertTrue(updateresponse.isSuccessful());
    }




}
