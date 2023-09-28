package DAO;

import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** This is the CountryFirstDivDao.java abstract class. */
public abstract class CountryFirstDivDao {

    /** RUNTIME ERROR corrected by SQLException.
     * This method retrieves the first level division names.
     * @throws SQLException the SQLException
     * @param  countryId the countryId parameter
     * @return divisionList*/
    public static List<String> division(int countryId) throws SQLException {
        List<String> divisionList = new ArrayList<String>();
        String sql = "SELECT Division FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            divisionList.add(rs.getString("Division"));
        }
        return divisionList;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method retrieves the country ID from the database.
     * @throws SQLException the SQLException
     * @param divisionId the divisionId parameter
     * @return result */
    public static int countryByFirstDiv(int divisionId) throws SQLException {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
        int result = 0;
        while (rs.next()) {
            result = rs.getInt("Country_ID");
        }
        return result;
    }
    /** RUNTIME ERROR corrected by SQLException.
     * This method retrieves the first level division names.
     * @throws SQLException the SQLException
     * @param divisionId the divisionId parameter
     * @return result */
    public static String firstDivName(int divisionId) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
        String result = null;
        while (rs.next()) {
            result = rs.getString("Division");
        }
        return result;
    }

    /** RUNTIME ERROR corrected by SQLException.
     * This method retrieves the first level division ID.
     * @throws SQLException the SQLException
     * @param divisionName the divisionName parameter
     * @return result */
    public static int firstDivId(String divisionName) throws SQLException {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divisionName);
        ResultSet rs = ps.executeQuery();
        int result = 0;
        while (rs.next()) {
            result = rs.getInt("Division_ID");
        }
        return result;
    }
}
