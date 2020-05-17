
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bagat
 */
public class CustomerOperations extends Operations {

    Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public CustomerOperations() {
        super();

        conn = Operations.conn;

    }

    void updateCustomer(String id, String companyName, String contactName, String contactTitle,
            String Address, String city, String region, String postalCode, String country, String phone, String Fax) {

        try {
            String query = "update customers set CompanyName = ? , ContactName = ? ,ContactTitle = ? , Address =  ?,"
                    + "City =  ? , Region = ?, PostalCode = ? , Country = ?, Phone = ? ,Fax = ? where CustomerId = ?  ";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, companyName);
            preparedStatement.setString(2, contactName);
            preparedStatement.setString(3, contactTitle);
            preparedStatement.setString(4, Address);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, region);
            preparedStatement.setString(7, postalCode);
            preparedStatement.setString(8, country);
            preparedStatement.setString(9, phone);
            preparedStatement.setString(10, Fax);
            preparedStatement.setString(11, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteCustomer(String id) {

        String query = "Delete from customers where CustomerId = ?";

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addCustomer(String id, String companyName, String contactName, String contactTitle,
            String Address, String city, String region, String postalCode, String country, String phone, String Fax) {

        String query = " insert into customers (CustomerId,CompanyName, ContactName ,ContactTitle, Address,"
                + "City  , Region , PostalCode  , Country ,Phone, Fax  ) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, companyName);
            preparedStatement.setString(3, contactName);
            preparedStatement.setString(4, contactTitle);
            preparedStatement.setString(5, Address);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, region);
            preparedStatement.setString(8, postalCode);
            preparedStatement.setString(9, country);
            preparedStatement.setString(10, phone);
            preparedStatement.setString(11, Fax);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Customers> getOperations() {
        ArrayList<Customers> customers = new ArrayList<Customers>();

        try {
            statement = conn.createStatement();
            String query = "Select * from Customers ";
            ResultSet rs = statement.executeQuery(query);

            while (!rs.next() == false) {

                String customerId = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String Phone = rs.getString("Phone");
                String Fax = rs.getString("Fax");

         
                customers.add(new Customers(customerId, CompanyName, ContactName, ContactTitle, Address, City,
                        Region, PostalCode, Country, Phone, Fax));

            }

            return customers;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    /* 
    public static void main(String[] args) {
        
        CustomerOperations s = new CustomerOperations();


       //s.addCustomer("BURA", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
       s.deleteCustomer("BURA");
        
    }*/
}
