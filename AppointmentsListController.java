package controller;

import DAO.AppointmentDao;
import helper.MiscInterface;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**This is the class AppointmentsListController.java. It controls the Appointments List form. */
public class AppointmentsListController implements Initializable {

    public TableView<Appointment> appointmentsTable;
    public TableColumn<Appointment, Integer> appointmentIdCol;
    public TableColumn<Appointment, String> titleCol;
    public TableColumn<Appointment, String> descriptionCol;
    public TableColumn<Appointment, String> locationCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, Timestamp> startCol;
    public TableColumn<Appointment, Timestamp> endCol;
    public TableColumn<Appointment, Integer> customerIdCol;
    public TableColumn<Appointment, Integer> userIdCol;
    public TableColumn<Appointment, Integer> contactIdCol;
    public RadioButton byMonthRB;
    public RadioButton byWeekRB;
    public ToggleGroup toggleGroup;
    public RadioButton allTimeRB;

    /**RUNTIME ERROR corrected by try-catch SQLException.
     * This is the first method called in the AppointmentsListController.java class.
     * It populates the Appointments tableview. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointmentsTable.setItems(AppointmentDao.selectAllAppointments());

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        } catch (SQLException throwable) {
        throwable.printStackTrace();
        }
    }

    /**RUNTIME ERROR corrected by try-catch SQLException.
     * This method responds to the allTimeRB radio button. It populates the tableview with all appointments.
     * @param actionEvent the actionEvent parameter*/
    public void onAllTime(ActionEvent actionEvent) {
        try {
            appointmentsTable.setItems(AppointmentDao.selectAllAppointments());

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by try-catch SQLException. This method responds to the byMonthRB radio button.
     * It populates the tableview with appointments for the current month.
     * @param actionEvent the actionEvent parameter*/
    public void onByMonth(ActionEvent actionEvent) {
        try {
            appointmentsTable.setItems(AppointmentDao.selectAppointmentsByMonth());

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by try-catch SQLException. This method responds to the byWeekRB radio button.
     * It populates the tableview with appointments for the current week.
     * @param actionEvent the actionEvent parameter*/
    public void onByWeek(ActionEvent actionEvent) {
        try {
            appointmentsTable.setItems(AppointmentDao.selectAppointmentsByWeek());

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /** RUNTIME ERROR corrected by IOException.
     * This method changes to the add appointment form.
     * @throws  IOException the IOException
     * @param actionEvent the actionEvent parameter */
    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsAdd.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** RUNTIME ERROR corrected by try-catch NullPointerException and IOException.
     * This method changes to the update appointment form.
     * @param actionEvent the actionEvent parameter */
    public void onUpdateAppointment(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/appointmentsUpdate.fxml"));
            loader.load();

            AppointmentsUpdateController ua = loader.getController();
            ua.appointmentData(appointmentsTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (NullPointerException | IOException n) {
            //ignore
        }

    }

    /** RUNTIME ERROR corrected by SQLException, and try-catch NullPointerException and IOException.
     * This method deletes the appointment selected in the tableview.
     * @throws SQLException the SQLException
     * @param actionEvent the actionEvent parameter*/
    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        try {
            int customerId = appointmentsTable.getSelectionModel().getSelectedItem().getCustomerId();
            for (Appointment app: AppointmentDao.selectAppointmentsByCustomer(customerId)){
                int appointmentId = app.getAppointmentId();
                String type = app.getType();
                MiscInterface errMsg = (s) -> new Alert(Alert.AlertType.WARNING, s).showAndWait();
                errMsg.doSomething("Appointment Cancelled \nAppointment ID: " +appointmentId+ "\nType: " +type);
            }
            int appointmentId = appointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId();
            AppointmentDao.deleteAppointment(appointmentId);
            appointmentsTable.setItems(AppointmentDao.selectAllAppointments());
        }catch (NullPointerException | IOException n) {
            //ignore
        }
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

    /**This method exits the Java program.
     * @param actionEvent the actionEvent parameter*/
    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        JDBC.closeConnection();
    }

    /** RUNTIME ERROR corrected by IOException. This method changes the scene to the Reports scene.
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
