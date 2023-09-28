package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/** This is the AppointmentDao.java abstract class. It sends information to and from the database. */
public abstract class AppointmentDao {

    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    /** RUNTIME ERROR corrected by SQLException.
     * This method queries the database for all the appointments information and places them in an ObservableList.
     * @throws SQLException the SQLException
     * @return appointmentList */
    public static ObservableList<Appointment> selectAllAppointments() throws SQLException {
        appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            appointmentList.add(new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId));
        }
        return appointmentList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method queries the database for appointment information and places them in an ObservableList.
     * @throws SQLException the SQLException
     * @param customerId the customerId parameter
     * @return appointmentList */
    public static ObservableList<Appointment> selectAppointmentsByCustomer(int customerId) throws SQLException {
        appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            appointmentList.add(new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId));
        }
        return appointmentList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method queries the database for appointment information and places them in an ObservableList.
     * @throws SQLException the SQLException
     * @param contactId the contactId parameter
     * @return appointmentList */
    public static ObservableList<Appointment> selectAppointmentsByContact(int contactId) throws SQLException {
        appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            contactId = rs.getInt("Contact_ID");

            appointmentList.add(new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId));
        }
        return appointmentList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method displays all the data in the current month into the Appointments Tableview.
     * @throws SQLException the SQLException
     * @return appointmentList */
    public static ObservableList<Appointment> selectAppointmentsByMonth() throws SQLException {
        appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW())";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            appointmentList.add(new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId));
        }
        return appointmentList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method displays all the data in the current week into the Appointments Tableview.
     * @throws SQLException the SQLException
     * @return appointmentList */
    public static ObservableList<Appointment> selectAppointmentsByWeek() throws SQLException {
        appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(NOW())";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            appointmentList.add(new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId));
        }
        return appointmentList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method displays an alert when there is an appointment in 15 minutes.
     * @throws SQLException the SQLException
     * @return result */
    public static String appointmentAlert() throws SQLException {

        String sql = "SELECT * FROM appointments WHERE TIMESTAMP(Start) BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setObject(1, Instant.now());
        ps.setObject(2, Instant.now().plus(15, ChronoUnit.MINUTES));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String start = rs.getString("Start");
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    ResourceBundle rb = ResourceBundle.getBundle("helper/Lang", Locale.getDefault());
                    String result = String.format((rb.getString("appoinmentSoon")) +":\n"+(rb.getString("appointmentID"))+": "+appointmentId+"\n"+(rb.getString("date&time"))+": " + start);
                    return result;
                }else {
                    String result = String.format("You have an appointment starting soon:\nAppointment ID: " + appointmentId + "\nDate & Time: " + start);
                    return result;
                }
        } else {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("helper/Lang", Locale.getDefault());
                String result = String.format(rb.getString("noAppointments"));
                return result;
            }else{
                String result = String.format("You have no pending appointments");
                return result;
            }
        }
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method adds new appointments.
     * @throws SQLException the SQLException
     * @param title the title parameter
     * @param  description the description parameter
     * @param location the location parameter
     * @param type the type parameter
     * @param start the start parameter
     * @param end  the end parameter
     * @param customerId the customerId parameter
     * @param userId the userId parameter
     * @param contactId the contactId parameter
     * @return rowsAffected */
    public static int insertNewAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(null,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method updates appointments.
     * @throws SQLException the SQLException
     *  @param appointmentId the appointmentId parameter
     *  @param title the title paramter
     *  @param  description the description parameter
     *  @param location the location parameter
     *  @param type the type parameter
     *  @param start the start parameter
     *  @param end  the end parameter
     *  @param customerId the customerId parameter
     *  @param userId the userId parameter
     *  @param contactId the contactId parameter
     *  @return rowsAffected */
    public static int updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes appointments.
     * @param appointmentId the appointmentId parameter
     * @return 0 */
    public static int deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected;
        } catch (SQLException s) {
            System.out.println("Error in AppointmentDAO");
            return 0;
        }
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method populates the type comboBox in the Reports scene.
     * @throws SQLException the SQLException
     * @param month the month parameter
     * @return typeList */
    public static List<String> type(String month) throws SQLException {
        List<String> typeList = new ArrayList<String>();
        String sql = "SELECT Type FROM appointments WHERE MONTHNAME(Start) = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            typeList.add(rs.getString("Type"));
        }
        Set<String> set = new HashSet<>(typeList);
        typeList.clear();
        typeList.addAll(set);
        return typeList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method displays the total appointments by month and type in the reports scene.
     * @throws SQLException the SQLException
     * @param month the month parameter
     * @param type the type parameter
     * @return total */
    public static int totalByMonthAndType(String month, String type) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE MONTHNAME(Start) = ? AND Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, month);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        int total = 0;
        while(rs.next()){
            total = total + 1;
        }
        return total;
    }
}
