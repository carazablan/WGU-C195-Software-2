package controller;

import DAO.*;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

/** This is the ReportsController.java class. It controls the reports tableview scene.*/
public class ReportsController implements Initializable {
    public ComboBox <Integer> contactIdCB;
    public TableView <Appointment> contactSchedTV;
    public TableColumn <Appointment, Integer> appIdCsCol;
    public TableColumn <Appointment, String> titleCsCol;
    public TableColumn <Appointment, String> typeCsCol;
    public TableColumn <Appointment, String> descCsCol;
    public TableColumn <Appointment, String> startCsCol;
    public TableColumn <Appointment, String> endCsCol;
    public TableColumn <Appointment, Integer> custIdCsCol;

    public Label totalResultLbl;
    public ComboBox <String> appMonthCB;
    public ComboBox <String> appTypeCB;

    public ComboBox <String> countryCB;
    public ListView <String> stateList;

    public TextArea loginTxtArea;

    private String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};

    /** This is the first method called in the ReportsController.java class. It sets the information into the textfield, and comboBoxes.
     * @param resourceBundle the resourceBundle parameter */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Scanner s = new Scanner(new File ("login_activity.txt"));
            while (s.hasNext()) {
                if (s.hasNextLine()) {
                    loginTxtArea.appendText(s.nextLine() + "\n");
                } else {
                    loginTxtArea.appendText(s.next() + "");
                }
            }
            countryCB.getItems().addAll(CountryDao.country());
            contactIdCB.getItems().addAll(ContactDao.contactId());
            appMonthCB.getItems().addAll(months);
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by SQLException. This method populates the type comboBox when the month comboBox is triggered.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter */
    public void onAppMonthCB(ActionEvent actionEvent) throws SQLException {
        appTypeCB.getItems().clear();
        appMonthCB.getSelectionModel().getSelectedItem();
        String month = appMonthCB.getSelectionModel().getSelectedItem();
        appTypeCB.getItems().addAll(AppointmentDao.type(month));
    }

    /** RUNTIME ERROR corrected by SQLException. This method displays the total when the type comboBox is triggered.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter */
    public void onAppTypeCB(ActionEvent actionEvent) throws SQLException {
        appTypeCB.getSelectionModel().getSelectedItem();
        String month = appMonthCB.getSelectionModel().getSelectedItem();
        String type = appTypeCB.getSelectionModel().getSelectedItem();
        totalResultLbl.setText(String.valueOf(AppointmentDao.totalByMonthAndType(month, type)));
    }

    /** RUNTIME ERROR corrected by try-catch SQLException. This method populates the tableview when the contactId comboBox is triggered.
     * @param actionEvent the actionEvent parameter */
    public void onSelectContactId(ActionEvent actionEvent) {
        try {
            contactSchedTV.setItems(AppointmentDao.selectAppointmentsByContact(contactIdCB.getValue()));

            appIdCsCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCsCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeCsCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            descCsCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            startCsCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCsCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            custIdCsCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by SQLException. This method populates the listview when the country comboBox is triggered.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter */
    public void onCountrySelect(ActionEvent actionEvent) throws SQLException {
        stateList.getItems().clear();
        int countryId = CountryDao.countryId(countryCB.getSelectionModel().getSelectedItem());
        stateList.getItems().addAll(CountryFirstDivDao.division(countryId));
    }

    /** RUNTIME ERROR corrected by IOException .
     * This method changes the scene to the Customers tableview.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onCustomersView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customersList.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** RUNTIME ERROR corrected by IOException .
     * This method changes the scene to the Appointments tableview.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onAppointmentsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsList.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method exits the Java program.
     * @param actionEvent the actionEvent parameter*/
    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        JDBC.closeConnection();
    }


}
