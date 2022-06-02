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
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void ranking(ActionEvent e) {
        // switch to ranking page use rankingPage.fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("rankingPage.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void friends(ActionEvent e) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("friendsPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void settings(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("settingPage.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void knowledge(ActionEvent e) {
        // switch to ranking page use rankingPage.fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("knowledge-page.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // set stage cannot resizeable
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
