package helpers;

import enums.ContactField;
import models.Contact;

public class ContactGenerator {

    private static Contact createContact(boolean incorrect, String incorrectValue, ContactField fieldName){
        String name = NameAndLastNameGenerator.generateName();
        String lastName =  NameAndLastNameGenerator.generateLastName();
        String email = EmailGenerator.generateEmail(5,5,3);
        String phoneNumber = PhoneNumberGenerator.generatePhoneNumber();
        String address = AddressGenerator.generateAddress();
        String description = "test";

        if(incorrect && fieldName != null){
            switch (fieldName){
                case NAME:
                    name = incorrectValue;
                    break;
                case LAST_NAME:
                    lastName = incorrectValue;
                    break;
                case EMAIL:
                    email = incorrectValue;
                    break;
                case PHONE_NUMBER:
                    phoneNumber = incorrectValue;
                    break;
                case ADDRESS:
                    address = incorrectValue;
                    break;
                case DESCRIPTION:
                    description = incorrectValue;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);

            }
        }
        return new Contact(name, lastName, email, phoneNumber, address, description);
    }

    public static Contact createCorrectContact(){
        return createContact(false, null, null);
    }
    public static Contact createIncorrectContact(ContactField fieldName, String incorrectValue){
        return createContact(true, incorrectValue, fieldName);
    }
//*****************************************
    public static void main(String[] args) {
        Contact contact = createCorrectContact();
        System.out.println("Correct : "+contact.toString());
        System.out.println("****************************");
        Contact incorrectContact = createIncorrectContact(ContactField.EMAIL, "1");
        System.out.println("Incorrect contact : "+ incorrectContact.toString());
    }

}
