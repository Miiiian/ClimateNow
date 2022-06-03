package com.example.climatenow;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import static com.example.climatenow.LoginPageController.setButtonToEmptyWithTime;
import static com.example.climatenow.LoginPageController.whetherUsernameInHashMap;

public class SettingPageController {


    @FXML
    private Button backToHomePageButton;

    @FXML
    private Button showDialogButton;

    @FXML
    public Text textOnInputWindow;

    @FXML
    public TextField newInputTextField;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button changeUsernameButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;


    private HashMap<String, String> usernameAndPasswordMap = new HashMap<>();
    public static String statusOfChangePasswordOrUsername;
    private String URL = "jdbc:mysql://***:3306/database";
    private String USER = "database";
    private String MYSQLPASSWORD = "";

    @FXML
    void changeToLoginScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginScene.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void closeWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("settingPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("ClimateNow");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @function
     */
    @FXML
    void ClickChangePasswordButton(ActionEvent event) {
        statusOfChangePasswordOrUsername = "password";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("inputTextWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Change Password");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void ClickChangeUsernameButton(ActionEvent event) {
        statusOfChangePasswordOrUsername = "username";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("inputTextWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Change Username");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @function change password and close window, password get from usernameTextField
     */
    @FXML
    void changePasswordOrUsernameFromTextField(ActionEvent event) {
        String username = LoginPageController.getUsername();
        switch (statusOfChangePasswordOrUsername) {
            case "password":
                // change password
                if(LoginPageController.isValidPassword(this.newInputTextField.getText())){
                    String newPassword = this.newInputTextField.getText();
                    String sql = "UPDATE database.db SET userPassword = " + "'" + newPassword + "'"  + " WHERE userName = " + "'" + username + "'" ;
                    updatePasswordToDatabase(sql);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginScene.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                else{
                    showDialogButton.setText("Password must be 6 to 20 characters, contain letters, numbers, special characters");
                    showDialogButton.setVisible(true);
                    setButtonToEmptyWithTime(showDialogButton, 3);
                }
                break;
            case "username":
                loadMysqlIntoHashMap();
                //System.out.println(usernameAndPasswordMap);
                if (whetherUsernameInHashMap(this.newInputTextField.getText(), usernameAndPasswordMap)) {
                    showDialogButton.setText("This username already used by someone");
                    showDialogButton.setVisible(true);
                    setButtonToEmptyWithTime(showDialogButton, 2);
                } else{
                    if(LoginPageController.isValidUsername(this.newInputTextField.getText())) {// change username
                        String newUsername = this.newInputTextField.getText();
                        String sql1 = "UPDATE database.db SET userName = " + "'" + newUsername + "'" + " WHERE userName = " + "'" + username + "'" ;
                        updateUsernameToDatabase(sql1);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginScene.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setResizable(false);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else{
                        showDialogButton.setText("Username must be 3 to 15 characters, contain letter, numbers, special symbol \" _-\".");
                        showDialogButton.setVisible(true);
                        setButtonToEmptyWithTime(showDialogButton, 3);
                }
            }
        }
    }



    @FXML
    void JumpOutYesAndNoWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("YesAndNoWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @function update username into database and close window
     * @param sql sql statement
     */
    void updateUsernameToDatabase(String sql) {
        try {
            java.sql.Connection connect = java.sql.DriverManager.getConnection(URL, USER, MYSQLPASSWORD);
            java.sql.Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @function update password into database and close window
     * @param sql sql statement
     */
    void updatePasswordToDatabase(String sql) {
        try {
            java.sql.Connection connect = java.sql.DriverManager.getConnection(URL, USER, MYSQLPASSWORD);
            java.sql.Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @function Connect to online mysql database and load username and password into usernameAndPasswordMap
     */
    public void loadMysqlIntoHashMap() {
        try {
            // System.out.println("start connecting to mysql");
            Connection connect = DriverManager.getConnection(URL, USER, MYSQLPASSWORD);
            // System.out.println("Success connect Mysql server!");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from database.db");

            while (rs.next()) {
                // String studentID = rs.getString("userID");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                usernameAndPasswordMap.put(userName, userPassword);  // put userName and userPassword into HashMap

                // System.out.println("userID:" + studentID + "" + "\t" + "Name:" + userName + "\t" + "Password:" + userPassword + "\n");
            }
            rs.close();
            connect.close(); // 关闭连接
        } catch (Exception e) {
            System.out.print("get data error!");
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
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
