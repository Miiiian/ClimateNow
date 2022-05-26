/**
 * Version: 0.1
 * Team: ClimateNow
 * Leader: Chen
 */

package com.example.climatenow;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.climatenow.QuizPageController.loadQuestionHashMapAndList;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadQuestionHashMapAndList();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
