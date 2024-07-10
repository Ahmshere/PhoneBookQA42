package db;

import models.Contact;

import java.sql.*;

public class DatabaseReader {
    static  String url = "jdbc:mysql://localhost:3306/phonebook";
    static String username = "root";
    static String password = "577561";

public static Contact readContactFromDatabase(String id) throws SQLException {
    Connection connection = DriverManager.getConnection(url, username, password);
    System.out.println("Connection successful!");
    String query = "SELECT * FROM contacts WHERE id= ? ";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, id);
    ResultSet resultSet = preparedStatement.executeQuery();

    Contact contact = null;
    if (resultSet.next()){
        contact = new Contact();
        contact.setId(resultSet.getString("id"));
        contact.setName(resultSet.getString("name"));
        contact.setLastName(resultSet.getString("lastname"));
        contact.setEmail(resultSet.getString("email"));
        contact.setPhone(resultSet.getString("phone"));
        contact.setAddress(resultSet.getString("address"));
        contact.setDescription(resultSet.getString("description"));
    }
    else {
        System.out.println("No contact found using id " +id);
    }
    preparedStatement.close();
    connection.close();
    return contact;

}




}
