package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** This is the CustomerDao.java abstract class. */
public abstract class CustomerDao {

    private static  ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /** RUNTIME ERROR corrected by SQLException.
     * This method queries the database for all the customers information and places them in an ObservableList.
     * @throws SQLException the SQLException
     * @return customerList */
    public static ObservableList<Customer> selectAllCustomers() throws SQLException {

        customerList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");

            customerList.add(new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionId));
        }
        return customerList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method queries the database for customer information and places them in an ArrayList.
     * @throws SQLException the SQLException
     * @return customerIdList */
    public static List<Integer> customerId() throws SQLException {
        List<Integer> customerIdList = new ArrayList<Integer>();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            customerIdList.add(rs.getInt("Customer_ID"));
        }
        return customerIdList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method adds new customers.
     * @throws SQLException the SQLException
     * @param customerId the customerId parameter
     * @param  customerName the customerName parameter
     * @param address the address parameter
     * @param postalCode the postalCode parameter
     * @param phoneNumber the phoneNumber parameter
     * @param divisionId the divisionId parameter
     * @return rowsAffected */
    public static int insertNewCustomer(String customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "INSERT INTO client_schedule.customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerId);
        ps.setString(2, customerName);
        ps.setString(3, address);
        ps.setString(4, postalCode);
        ps.setString(5, phoneNumber);
        ps.setInt(6, divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method updates customers.
     * @throws SQLException the SQLException
     * @param customerId the customerId parameter
     * @param  customerName the customerName parameter
     * @param address the address parameter
     * @param postalCode the postalCode parameter
     * @param phoneNumber the phoneNumber parameter
     * @param divisionId the divisionId parameter
     * @return rowsAffected */
    public static int updateCustomer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes customers.
     * @param customerId the customerId parameter
     * @return 0 */
    public static int deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected);
            return rowsAffected;
        } catch (SQLException s) {
            System.out.println("Error in CustomerDAO");
            return 0;
        }
    }
}
