package com.example.climatenow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private ImageView firstImageView;

    public void quiz(ActionEvent e) {
        // switch to quiz page use QuizQuestions.fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("QuizQuestions.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void ranking(ActionEvent e) {
        System.out.println("Ranking list opened");
        // TODO: switch to Ranking list scene
    }

    public void friends(ActionEvent e) {
        System.out.println("Friends list opened");
        // TODO: switch to Friends list scene
    }

    public void settings(ActionEvent e) {
        System.out.println("Settings opened");
        // TODO: switch to Settings scene
    }

    public void knowledge(ActionEvent e) {
        System.out.println("Knowledge opened");
        // TODO: switch to Knowledge scene
    }


    @FXML
    public void displayImage(MouseEvent event) {
        try {
            String url = "https://www.un.org/en/climatechange";
            java.net.URI uri = java.net.URI.create(url);

            java.awt.Desktop dp = java.awt.Desktop.getDesktop();

            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayGlobal(MouseEvent event) {
        try {
            String url = "https://www.livescience.com/37057-global-warming-effects.html";
            java.net.URI uri = java.net.URI.create(url);

            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayCarbon(MouseEvent event) {
        try {
            String url = "https://www.britannica.com/science/greenhouse-gas";
            java.net.URI uri = java.net.URI.create(url);

            java.awt.Desktop dp = java.awt.Desktop.getDesktop();

            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
