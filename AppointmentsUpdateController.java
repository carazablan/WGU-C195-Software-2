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
import model.Appointment;
import helper.MyTimeZone;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/** This is the AppointmentsUpdateController.java class. It controls the form for updating existing appointments. */
public class AppointmentsUpdateController implements Initializable {
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
    private Appointment selectedAppointment = null;

    /** RUNTIME ERROR corrected by try-catch SQLException. This is the first method called in the AppointmentsUpdateController.java class.
     * It sets the local and zoned times.
     * @param resourceBundle the resourceBundle parameter */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /** This method sets information from the selected appointment in the previous scene.
     * @param appointment the actionEvent parameter*/
    public void appointmentData(Appointment appointment) {
        selectedAppointment = appointment;
        appointmentIdTxt.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        titleTxt.setText(String.valueOf(selectedAppointment.getTitle()));
        descriptionTxt.setText(String.valueOf(selectedAppointment.getDescription()));
        locationTxt.setText(String.valueOf(selectedAppointment.getLocation()));
        typeTxt.setText(String.valueOf(selectedAppointment.getType()));
        contactIdCB.setValue(selectedAppointment.getContactId());
        userIdCB.setValue(selectedAppointment.getUserId());
        customerIdCB.setValue(selectedAppointment.getCustomerId());
        startDateDP.setValue(selectedAppointment.getStart().toLocalDate());
        endDateDP.setValue(selectedAppointment.getEnd().toLocalDate());
        startTimeCB.setValue(selectedAppointment.getStart().toLocalTime());
        endTimeCB.setValue(selectedAppointment.getEnd().toLocalTime());
    }

    /** RUNTIME ERROR corrected by try-catch SQLException and IOException.
     * LAMBDA used to simplify code, and reduce amount of lines needed.
     * This method saves any changes made and switches back to the Appointments List scene.
     * @param actionEvent the actionEvent parameter */
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

            }else if(AppointmentHelper.appOverlapCheck(startDateTime, endDateTime, customerIdCB.getValue(), Integer.parseInt(appointmentIdTxt.getText()))) {
                errMsg.doSomething("Appointment time overlaps.");

            } else {
                int appointmentId = selectedAppointment.getAppointmentId();
                String title = titleTxt.getText();
                String description = descriptionTxt.getText();
                String location = locationTxt.getText();
                String type = typeTxt.getText();
                int customerId = customerIdCB.getValue();
                int userId = userIdCB.getValue();
                int contactId = contactIdCB.getValue();

                AppointmentDao.updateAppointment(appointmentId, title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId);

                Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsList.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            }catch(IOException | SQLException e){
                e.printStackTrace();
            }
        }

    /** RUNTIME ERROR corrected by IOException. This method cancels any changes attempted and switches back to the Appointments List scene.
     * @throws IOException the IOException
     * @param actionEvent the actionEvent parameter*/
        public void onCancel(ActionEvent actionEvent)  throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes may not have been saved, cancel anyway?");
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
