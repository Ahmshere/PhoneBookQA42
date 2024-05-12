package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements TestHelper {


    @Test
    public  void clearContacts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_ALL_CONTACTS)
                .addHeader("Authorization", PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        System.out.println("Code: "+response.code());
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("Message"+contactResponseModel.getMessage());
    }

}
