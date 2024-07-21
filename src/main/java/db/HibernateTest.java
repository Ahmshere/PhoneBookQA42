package db;

import helpers.ContactGenerator;
import jakarta.persistence.Query;
import models.Contact;
import org.hibernate.Session;

import java.util.UUID;

public class HibernateTest {

    public static void main(String[] args) {
        Contact cont = addNewContact();
        System.out.println("CONTACT: "+cont.getId());


        Contact c2 = getContactById(cont.getId());
        System.out.println("CONTACT2: "+c2.getId());

    }


    public static Contact addNewContact(){
        Contact contact = ContactGenerator.createCorrectContact();
        try (Session session  = HibernateUtil.getSession()){
            session.beginTransaction();
            String idd = String.valueOf(UUID.randomUUID());

            System.out.println("ID: "+idd);

            contact.setId(idd);
            session.save(contact);
            session.getTransaction().commit();
        }catch (Throwable throwable){}
        return contact;
    }

    public static Contact getContactById(String id){
        Contact contact = null;
        try (Session session  = HibernateUtil.getSession()){
            session.beginTransaction();
            Query query = session.createQuery("FROM Contact where id = :id");
            query.setParameter("id", id);
            contact = (Contact) query.getSingleResult();
            session.getTransaction().commit();
            System.out.println(contact.getId());

        }catch (Throwable throwable){}
        return contact;
    }
}
