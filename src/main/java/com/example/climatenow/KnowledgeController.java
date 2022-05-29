package com.example.climatenow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class KnowledgeController {

    @FXML
    private ImageView article1;

    @FXML
    private ImageView article2;

    @FXML
    private ImageView article3;

    @FXML
    private ImageView article4;

    @FXML
    private ImageView article5;

    @FXML
    private ImageView article6;

    @FXML
    private Button homeButton;

    @FXML
    void switchToArticle1(MouseEvent event) {
        try {
            String url = "https://climate.nasa.gov/causes/";
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
    void switchToArticle2(MouseEvent event) {
        try {
            String url = "https://climate.nasa.gov/effects/";
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
    void switchToArticle3(MouseEvent event) {
        try {
            String url = "https://www.un.org/sustainabledevelopment/sdgbookclub-13-archive/";
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
    void switchToArticle4(MouseEvent event) {
        try {
            String url = "https://www.un.org/en/climatechange/what-is-climate-change";
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
    void switchToArticle5(MouseEvent event) {
        try {
            String url = "https://climate.nasa.gov/vital-signs/carbon-dioxide/";
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
    void switchToArticle6(MouseEvent event) {
        try {
            String url = "https://www.un.org/actnow?gclid=CjwKCAjwkMeUBhBuEiwA4hpqEFrfsvbkRVzfIcnfA375pNIiKnYMxOai63cgsyhEUZ_IvTIpWlOiDhoCngYQAvD_BwE";
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
    void switchToHomePage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home-page.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
