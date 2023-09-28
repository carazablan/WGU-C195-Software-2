package controller;

import DAO.CustomerDao;
import helper.MiscInterface;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This is the CustomersListController.java class. It controls the customers list form. */
public class CustomersListController implements Initializable {

    public TableView <Customer> customersTable;
    public TableColumn <Customer, Integer> customerIdCol;
    public TableColumn <Customer, String> customerNameCol;
    public TableColumn <Customer, String> addressCol;
    public TableColumn <Customer, Integer> postalCodeCol;
    public TableColumn <Customer, Integer> phoneNumberCol;
    public TableColumn <Customer, Integer> divisionIdCol;

    /**This is the first method called in the CustomersListController.java class. It populates the Customer tableview.
     * @param resourceBundle the resourceBundle parameter */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customersTable.setItems(CustomerDao.selectAllCustomers());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
    }

    /** RUNTIME ERROR corrected by IOException. This method switches to the add customer form.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customersAdd.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** RUNTIME ERROR corrected by try-catch NullPointerException, IOException, and SQLException. This method switches to the update customer form.
     * @param actionEvent the actionEvent parameter*/
    public void onUpdateCustomer(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/customersUpdate.fxml"));
            loader.load();

            CustomersUpdateController ca = loader.getController();
            ca.customerData(customersTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (NullPointerException | IOException | SQLException n) {
            //ignore
        }
    }

    /** RUNTIME ERROR corrected by SQLException, and try-catch NullPointerException, and IOException.
     * LAMBDA used for readability, and fewer lines needed.
     * This method deletes a customer and its appointments.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter*/
    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        try {
            int customerId = customersTable.getSelectionModel().getSelectedItem().getCustomerId();
            int rowsAffected = CustomerDao.deleteCustomer(customerId);
            customersTable.setItems(CustomerDao.selectAllCustomers());
            if (rowsAffected > 0) {
                MiscInterface errMsg = (s) -> new Alert(Alert.AlertType.WARNING, s).showAndWait();
                errMsg.doSomething("Customer deleted.");
            } else {
                System.out.println("No Customer Selected");
            }
        }catch (NullPointerException | IOException n) {
            //ignore
        }
    }

    /** RUNTIME ERROR corrected by IOException. This method switches to the Appointments tableview scene.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onAppointmentsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsList.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** RUNTIME ERROR corrected by IOException. This method exits the java application.
     * @param actionEvent the actionEvent parameter*/
    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        JDBC.closeConnection();
    }

    /** RUNTIME ERROR corrected by IOException. This method switches to the Reports scene.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onReportsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
