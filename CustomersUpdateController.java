package controller;

import DAO.*;
import helper.MiscInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This is the CustomersUpdateController.java class. It controls the customer add form. */
public class CustomersUpdateController implements Initializable {
    public TextField lastNameTxt;
    public TextField firstNameTxt;
    public TextField customerIdTxt;
    public TextField addressTxt;
    public TextField postalCodeTxt;
    public TextField phoneTxt;
    public ComboBox <String> firstLevelCB;
    public ComboBox <String> countryCB;
    private Customer selectedCustomer = null;

    /** This is the first method called in the CustomersUpdateController.java class. It sets the country list into its comboBox.
     * @param resourceBundle the resourceBundle parameter */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countryCB.getItems().addAll(CountryDao.country());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by SQLException. This method populates the form with the customer information selected from the previous scene.
     * @throws SQLException the SQLException
     * @param customer the customer parameter*/
    public void customerData(Customer customer) throws SQLException {
        selectedCustomer = customer;

        String customerName = String.valueOf(selectedCustomer.getCustomerName());
        int idx = customerName.lastIndexOf(' ');
        String firstName = customerName.substring(0, idx);
        String lastName  = customerName.substring(idx + 1);

        customerIdTxt.setText(String.valueOf(selectedCustomer.getCustomerId()));
        lastNameTxt.setText(lastName);
        firstNameTxt.setText(firstName);
        addressTxt.setText(String.valueOf(selectedCustomer.getAddress()));
        postalCodeTxt.setText(String.valueOf(selectedCustomer.getPostalCode()));
        phoneTxt.setText(String.valueOf(selectedCustomer.getPhoneNumber()));
        countryCB.getSelectionModel().select(CountryDao.countryName(CountryFirstDivDao.countryByFirstDiv(selectedCustomer.getDivisionId())));
        firstLevelCB.getSelectionModel().select(CountryFirstDivDao.firstDivName(selectedCustomer.getDivisionId()));
        String countryName = String.valueOf(countryCB.getSelectionModel().getSelectedItem());
        int countryId = CountryDao.countryId(countryName);
        firstLevelCB.getItems().addAll(CountryFirstDivDao.division(countryId));
    }

    /** RUNTIME ERROR corrected by SQLException. This method populates the first level division comboBox when the country comboBox is triggered.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter*/
    public void onSelectCountry(ActionEvent actionEvent) throws SQLException {
        firstLevelCB.getItems().clear();
        countryCB.getSelectionModel().getSelectedItem();
        String countryName = String.valueOf(countryCB.getSelectionModel().getSelectedItem());
        int countryId = CountryDao.countryId(countryName);
        firstLevelCB.getItems().addAll(CountryFirstDivDao.division(countryId));
    }

    /** RUNTIME ERROR corrected by try-catch SQLException, IOException.
     * LAMBDA used to reduce lines needed.
     * This method saves any changes made and switches back to the Customers List scene.
     * @param actionEvent the actionEvent parameter*/
    public void onSave(ActionEvent actionEvent) {
        try {
            MiscInterface errMsg = (s) -> new Alert(Alert.AlertType.WARNING, s).showAndWait();

            if (firstNameTxt.getText().isBlank()) {
                errMsg.doSomething("Enter First Name");

            }else if (lastNameTxt.getText().isBlank()) {
                errMsg.doSomething("Enter Last Name");

            }else if (addressTxt.getText().isBlank()) {
                errMsg.doSomething("Enter Address");

            }else if (postalCodeTxt.getText().isBlank()) {
                errMsg.doSomething("Enter Postal Code");

            }else if (phoneTxt.getText().isBlank()) {
                errMsg.doSomething("Enter Phone Number");

            }else if (countryCB.getSelectionModel().isEmpty()) {
                    errMsg.doSomething("Please select a Country");

            }else if (firstLevelCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Please select a State/Province");

            }else{
                int customerId = selectedCustomer.getCustomerId();
                String customerName = firstNameTxt.getText() + " " + lastNameTxt.getText();
                String address = addressTxt.getText();
                String postalCode = postalCodeTxt.getText();
                String phoneNumber = phoneTxt.getText();
                int divisionId = CountryFirstDivDao.firstDivId(String.valueOf(firstLevelCB.getValue()));

                CustomerDao.updateCustomer(customerId, customerName, address, postalCode, phoneNumber, divisionId);

                Parent root = FXMLLoader.load(getClass().getResource("/view/customersList.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (SQLException | IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /** RUNTIME ERROR corrected by IOException. This method cancels any changes attempted and switches back to the Customers List scene.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onCancel(ActionEvent actionEvent)  throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes not saved.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/customersList.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
