package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** This is the CountryDao.java abstract class. */
public abstract class CountryDao {
    public static ObservableList<Country> selectAllCountries() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Country> countryList = FXCollections.observableArrayList();

        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");

            countryList.add(new Country(countryId, country));
        }
        return countryList;
    }

    public static List<String> country() throws SQLException {
        List<String> countryList = new ArrayList<String>();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            countryList.add(rs.getString("Country"));
        }
        return countryList;
    }

    public static String countryName(int countryId) throws SQLException {
        String sql = "SELECT Country FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();
        String countryName = null;
        while (rs.next()) {
            countryName = rs.getString("Country");
        }
        return countryName;
    }

    public static int countryId(String countryName) throws SQLException {
        String sql = "SELECT Country_ID FROM countries WHERE Country = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();
        int countryId = 0;
        while (rs.next()) {
            countryId = rs.getInt("Country_ID");
        }
        return countryId;
    }
}
