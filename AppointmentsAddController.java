package controller;

import DAO.AppointmentDao;
import DAO.ContactDao;
import DAO.CustomerDao;
import DAO.UserDao;
import helper.AppointmentHelper;
import helper.MiscInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import helper.MyTimeZone;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**This is the class AppointmentsAddController.java. It controls the form for adding new appointments. */
public class AppointmentsAddController implements Initializable {
    public TextField appointmentIdTxt;
    public TextField titleTxt;
    public TextField descriptionTxt;
    public TextField locationTxt;
    public TextField typeTxt;
    public DatePicker startDateDP;
    public DatePicker endDateDP;
    public ComboBox <Integer> contactIdCB;
    public ChoiceBox <LocalTime> startTimeCB;
    public ChoiceBox <LocalTime> endTimeCB;
    public ComboBox <Integer> userIdCB;
    public ComboBox <Integer> customerIdCB;

    /** RUNTIME ERROR corrected by try-catch SQLException.
     * This is the first method called in the AppointmentsAddController.java class.
     * It sets the local time and zoned time values*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            startDateDP.setValue(LocalDate.now());
            endDateDP.setValue(LocalDate.now());
            startTimeCB.setValue(LocalTime.now());
            endTimeCB.setValue(LocalTime.now());

            LocalTime nyLTStartHr = MyTimeZone.nyTime().toLocalTime();
            LocalTime nyLTEndHr = MyTimeZone.nyTime().toLocalTime().plusHours(14);

            while(nyLTStartHr.isBefore(nyLTEndHr.plusSeconds(1))){
                startTimeCB.getItems().add(nyLTStartHr);
                endTimeCB.getItems().add(nyLTStartHr);
                nyLTStartHr = nyLTStartHr.plusMinutes(15);
            }

            userIdCB.getItems().addAll(UserDao.userId());
            customerIdCB.getItems().addAll(CustomerDao.customerId());
            contactIdCB.getItems().addAll(ContactDao.contactId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**RUNTIME ERROR Corrected with try-catch SQLException and IOException.
     * This method saves a new new Appointment.
     * @param actionEvent the actionEvent parameter*/
    public void onSave(ActionEvent actionEvent) {
        try {
            LocalDateTime startDateTime = LocalDateTime.of(startDateDP.getValue(), startTimeCB.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDateDP.getValue(), endTimeCB.getValue());

            MiscInterface errMsg = (s) -> new Alert(Alert.AlertType.WARNING, s).showAndWait();

            if (startTimeCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Select start time.");

            }else if (endTimeCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Select end time.");

            }else if (startDateDP.getValue() == null) {
                errMsg.doSomething("Select Start Date.");

            }else if (endDateDP.getValue() == null) {
                errMsg.doSomething("Select End Date.");

            }else if(titleTxt.getText().isBlank()) {
                errMsg.doSomething("Title is blank.");

            }else if(descriptionTxt.getText().isBlank()) {
                errMsg.doSomething("Description is blank.");

            }else if(locationTxt.getText().isBlank()) {
                errMsg.doSomething("Location is blank.");

            }else if(typeTxt.getText().isBlank()) {
                errMsg.doSomething("Type is blank.");

            }else if(userIdCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Select User ID.");

            }else if(contactIdCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Select Contact ID.");

            }else if(customerIdCB.getSelectionModel().isEmpty()) {
                errMsg.doSomething("Select Customer ID.");

            }else if (endDateTime.isBefore(startDateTime)) {
                errMsg.doSomething("Appointment end time must occur after start time.");

            }else if(AppointmentHelper.appOverlapCheck(startDateTime, endDateTime, customerIdCB.getValue(), -1)) {
                errMsg.doSomething("Appointment time overlaps.");

            }else {

                String appointmentId = null;
                String title = titleTxt.getText();
                String description = descriptionTxt.getText();
                String location = locationTxt.getText();
                String type = typeTxt.getText();
                int customerId = customerIdCB.getValue();
                int userId = userIdCB.getValue();
                int contactId = contactIdCB.getValue();

                AppointmentDao.insertNewAppointment(title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId);

                Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsList.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /** RUNTIME ERROR corrected by IOException. This method cancels any changes attempted and switches back to the Appointments List scene.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment not saved.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsList.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
