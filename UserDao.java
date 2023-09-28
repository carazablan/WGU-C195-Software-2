package DAO;

import helper.JDBC;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** This is the UserDao.java abstract class. */
public abstract class UserDao {

    /** RUNTIME ERROR corrected by try-catch SQLException.
     * This method checks the username and password.
     * @param userName the userName parameter
     * @param password the password parameter
     * @return null*/
    public static User Validation(String userName, String password) {
        try {
            String sql = "SELECT User_Name, Password FROM client_schedule.users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User currentUser = new User(rs.getString("User_Name"));
                System.out.println(" Login Successful");
                return currentUser;
            } else {
                System.out.println(" Login Failed");
                return null;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method retrieves the user ID.
     * @throws SQLException the SQLException
     * @return userIdList */
    public static List<Integer> userId() throws SQLException {
        List<Integer> userIdList = new ArrayList<Integer>();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            userIdList.add(rs.getInt("User_ID"));

        }
        return userIdList;
    }
}
