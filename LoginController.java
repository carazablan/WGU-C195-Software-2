package controller;

import DAO.AppointmentDao;
import DAO.UserDao;
import helper.MyTimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

import static java.lang.String.*;

/** This is the class LoginController.java.
 * This is where the user logs in to gain access to the rest of the application.*/
public class LoginController implements Initializable {
    public Button loginButton;
    public TextField userName;
    public PasswordField password;
    public Label timeZoneLabel;
    public Label companyName;

    /** This the first method called.
     * It sets the Zone ID and detects the system default language settings.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZoneLabel.setText(valueOf(ZoneId.systemDefault()));

        if(Locale.getDefault().getLanguage().equals("fr")){
            ResourceBundle rb = ResourceBundle.getBundle("helper/Lang", Locale.getDefault());
            companyName.setText(rb.getString("companyName"));
            userName.setPromptText(rb.getString("username"));
            password.setPromptText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
        }
    }

    /** RUNTIME ERROR Corrected by Exception, and try/catch clause with IOException and SQLException. This Method is the Login form.
     * It accepts a username and password, and checks for system language settings.
     * @throws Exception the Exception
     * @param actionEvent the actionEvent parameter */
    public void onLogin(ActionEvent actionEvent) throws Exception {
        try {
            MyTimeZone.myLocalTime();
            String userNameLog = valueOf(userName.getText());
            String passwordLog = valueOf(password.getText());
            User tryLogin = UserDao.Validation(userNameLog, passwordLog);
            if(tryLogin == null){
                loginTracker("Login Failed");
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    ResourceBundle rb = ResourceBundle.getBundle("helper/Lang", Locale.getDefault());
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(rb.getString("warning"));
                    alert.setContentText(rb.getString("warningText"));
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("Please use a valid Username and Password");
                    alert.showAndWait();
                }
            }else {
                loginTracker("Login Successful");
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    ResourceBundle rb = ResourceBundle.getBundle("helper/Lang", Locale.getDefault());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("PendingAppointment"));
                    alert.setContentText(AppointmentDao.appointmentAlert());
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pending Appointment");
                    alert.setContentText(AppointmentDao.appointmentAlert());
                    alert.showAndWait();
                }
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentsList.fxml")));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
            }
        }catch (IOException | SQLException e) {
            //ignore
        }
    }

    /** This method tracks logins and appends them to the login_activity.txt
     * @param loggedIn
     * @throws Exception
     */
    private void loginTracker(String loggedIn) throws Exception {
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
        pw.append("Date and Time: ").append(MyTimeZone.myLocalTime()).append("  |  Username: ").append(userName.getText()).append("  |  ").append(loggedIn).append("\n");
        pw.close();
    }
}
