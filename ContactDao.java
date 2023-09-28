package DAO;

import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** THis is the ContactDao.java abstract class. */
public abstract class ContactDao {

    public static List<Integer> contactId() throws SQLException {
        List<Integer> contactIdList = new ArrayList<Integer>();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactIdList.add(rs.getInt("Contact_ID"));
        }
        return contactIdList;
    }

}
