package com.example.climatenow;


import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;


public class LoginPageController {

    @FXML // fx:id="showDialogButton"
    private Button showDialogButton;

    @FXML // fx:id="password_text"
    private PasswordField password_text;

    @FXML // fx:id="username_text"
    private TextField username_text;

    private String usernameFromText, passwordFromText;
    private final HashMap<String, String> usernameAndPasswordMap = new HashMap<>();
    private static int inc = 0;
    private final String url = "jdbc:mysql://149.28.128.137:3306/database";
    private final String user = "database";
    private final String mySQLPassword = "bjTYFTm4N5sRzziR";
    private static String usernameForNow;

    /**
     * Make the incoming button parameter invisible after a time you set
     * @param button that need turn to invisible
     * @param time execution wait time
     */
    private void setButtonToEmptyWithTime(Button button, Integer time){
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(time)
        );
        visiblePause.setOnFinished(
                event -> button.setVisible(false)
        );
        visiblePause.play();
    }

    /**
     * @function Login exist user via the username that stored in database, and switch to Home Page
     */
    @FXML
    void loginClick(ActionEvent event) throws IOException {
        usernameFromText = username_text.getText();
        passwordFromText = password_text.getText();
        loadMysqlIntoHashMap();
        // System.out.println(usernameAndPasswordMap);
        if (whetherUsernameInHashMap(username_text.getText(), usernameAndPasswordMap)) {
            if (usernameAndPasswordMap.get(usernameFromText).equals(passwordFromText)) {
                // System.out.println("Successful Login");
                usernameForNow = usernameFromText;
                // Switch to Home Page
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home-page.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                showDialogButton.setText("Wrong Password");
                showDialogButton.setVisible(true);
                setButtonToEmptyWithTime(showDialogButton, 2);
            }
        } else {
            showDialogButton.setText("There have not this user");
            showDialogButton.setVisible(true);
            setButtonToEmptyWithTime(showDialogButton, 2);
        }
    }

    /**
     * @function Insert/Sign Up new user to database after validate username and password
     */
    @FXML
     void signUpClick() {
        usernameFromText = username_text.getText();
        passwordFromText = password_text.getText();
        loadMysqlIntoHashMap();
        // System.out.println(usernameAndPasswordMap);
        if (whetherUsernameInHashMap(usernameFromText, usernameAndPasswordMap)) {
            showDialogButton.setText("This username already used by someone");
            showDialogButton.setVisible(true);
            setButtonToEmptyWithTime(showDialogButton, 2);
        } else {
            if (isValidPassword(password_text.getText()) && isValidUsername(username_text.getText())) {
                try {
                    updateNewUserIntoMysql(usernameFromText, passwordFromText);
                    showDialogButton.setText("Successful Sign up");
                    showDialogButton.setVisible(true);
                    setButtonToEmptyWithTime(showDialogButton, 2);
                } catch (Exception e) {
                    System.out.println("Sign up method can't connect to database or something wrong");
                }
            } else if (!(isValidPassword(password_text.getText())) && isValidUsername(username_text.getText())) {
                showDialogButton.setText("Password must be 6 to 20 characters, contain letters, numbers, special characters");
                showDialogButton.setVisible(true);
                setButtonToEmptyWithTime(showDialogButton, 4);
            } else if (isValidPassword(password_text.getText()) && !(isValidUsername(username_text.getText()))) {
                showDialogButton.setText("Username must be 3 to 15 characters, contain letter, numbers, special symbol \" _-\".");
                showDialogButton.setVisible(true);
                setButtonToEmptyWithTime(showDialogButton, 4);
            } else if (!(isValidPassword(password_text.getText())) && !(isValidUsername(username_text.getText()))){
                showDialogButton.setText("Username must be 3 to 15 characters, contain letter, numbers, special symbol \" _-\".");
                showDialogButton.setVisible(true);
                setButtonToEmptyWithTime(showDialogButton, 4);
                showDialogButton.setText("Password must be 6 to 20 characters, contain letters, numbers, special characters");
                showDialogButton.setVisible(true);
                setButtonToEmptyWithTime(showDialogButton, 4);
            }
        }

    }

    /**
     * Validate password with regular expression
     * @param password that need test whether valid
     * Check whether the password contains 6 to 20 characters including letters, digits, and special characters
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // String.matches
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[`~!@#$%^&*()-=_+;':\",./<>?])(?=\\S+$).{6,20}$");
    }

    /**
     * Validate username with regular expression
     * @param username that need test whether valid
     * Check whether the username contains 3 to 15 characters can only contain letter, numbers or the special symbol " _-".
     */
    public static boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        // String.matches
        return username.matches("^[A-Za-z0-9_-]{3,15}$");
    }


    /**
     * @param username that need check in database
     * @param userHashMap that stored all username in database
     * @return ture username in HashMap, false username not in HashMap
     */
    public boolean whetherUsernameInHashMap(String username, HashMap<String, String> userHashMap) {
        // Check whether the system already has this username
        boolean whetherStringInHashMap = false;
        for (String key : userHashMap.keySet()) {
            if (username.equals(key)) {
                whetherStringInHashMap = true;
                break;
            }
        }
        return whetherStringInHashMap;
    }

    /**
     * @function Connect to online mysql database and load username and password into usernameAndPasswordMap
     */
    public void loadMysqlIntoHashMap() {
        try {
            // System.out.println("start connecting to mysql");
            Connection connect = DriverManager.getConnection(url, user, mySQLPassword);
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

    /**
     * Update New User's Username and Password Into MySQL database
     * @param username that need sign up and insert to database
     * @param password the new user's password
     */
    public void updateNewUserIntoMysql(String username, String password) {
        try {
            // System.out.println("start connecting");
            Connection connect = DriverManager.getConnection(url, user, mySQLPassword);
            // url   jdbc:mysql//(server address)/(database name) , user , passwd
            // System.out.println("Success connect Mysql server!");
            username = "'" + username + "'";
            password = "'" + password + "'";
            Statement stmt = connect.createStatement();
            String sql = "INSERT INTO database.db (userID, userName, userPassword, userMark) VALUES (" + "'" + getId() + "'" + ", " + username + ", " + password + ", '0')";
            // System.out.println("sign up SQL statement:    " + sql);
            stmt.executeUpdate(sql);
            connect.close(); // Close connection
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }

    /**
     * Generate a random integer ID for everyone sign up
     * @return (int) A random ID that length is 10
     */
    private static int getId() {

        long id = Long.parseLong(String.valueOf(System.currentTimeMillis())
                .substring(1, 10)
                .concat(String.valueOf(inc)));

        inc = (inc + 1) % 10;
        return Math.abs((int) id);

    }

    /**
     * get username who is a login now
     */
    public static String getUsername() {
        return usernameForNow;
    }
}
