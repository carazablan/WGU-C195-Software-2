package com.company;

import helper.MiscInterface;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/** This is the class Main.java. */
public class Main extends Application {

    /** LAMBDA placed in this method to improve readability.
     * this method takes you to the Login scene. */
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();

        MiscInterface f = (s) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(s));
                primaryStage.setScene(new Scene(root, 826, 499));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        f.doSomething("/view/login.fxml");
    }

    /**This is the main method. This is the first method called when this Java program is run.
     * @param args the arguments*/
    public static void main(String[] args) {
        launch(args);
    }

}
