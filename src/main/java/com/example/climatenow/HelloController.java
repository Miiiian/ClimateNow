package com.example.climatenow;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.sql.*;
import java.util.HashMap;


public class HelloController {

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginPoster"
    private ImageView loginPoster; // Value injected by FXMLLoader

    @FXML // fx:id="password_text"
    private PasswordField password_text; // Value injected by FXMLLoader

    @FXML // fx:id="signUpButton"
    private Button signUpButton; // Value injected by FXMLLoader

    @FXML // fx:id="username_text"
    private TextField username_text; // Value injected by FXMLLoader

    private String usernameFromText, passwordFromText;
    private HashMap<String, String> usernameAndPasswordMap = new HashMap<>();
    private static int inc = 0;


    @FXML
    void loginClick(ActionEvent event) {
        usernameFromText = username_text.getText();
        passwordFromText = password_text.getText();
        loadMysqlIntoHashMap();
        System.out.println("usernameAndPasswordMap" + usernameAndPasswordMap);
        System.out.println("usernameFromText" + usernameFromText);
        System.out.println("passwordFromText" + passwordFromText);


        System.out.println("Username in Hashmap" + usernameAndPasswordMap.get(usernameFromText));
        if (whetherUsernameInHashMap(username_text.getText(), usernameAndPasswordMap)) {
            if (usernameAndPasswordMap.get(usernameFromText).equals(passwordFromText)) {
                System.out.println("Successful Login");
                //TODO switchToHomeScene();
            } else {
                System.out.println("Wrong password.");
            }
        } else {
            System.out.println("There have not this user");
        }
    }

    @FXML
    void signUpClick(ActionEvent event) {
        usernameFromText = username_text.getText();
        passwordFromText = password_text.getText();
        loadMysqlIntoHashMap();
        if (whetherUsernameInHashMap(usernameFromText, usernameAndPasswordMap)) {
            System.out.println("This username already used by someone");
        } else {
            if (isValidPassword(password_text.getText())) {
                try {
                    updateNewUserIntoMysql(usernameFromText, passwordFromText);
                    System.out.println("Successful Sign Up");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Invalid Password or Username");
                System.out.println("The password must be 6 to 20 characters containing letters, numbers, and special characters");
            }
        }

    }

    /**
     * Check whether the password contains 6 to 20 characters including letters, digits, and special characters
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // String.matches
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[`~!@#$%^&*()-=_+;':\",./<>?])(?=\\S+$).{6,20}$");
    }


    public static void switchToHomeScene() {
        //TODO
    }


    /**
     * param: username
     * param: userHashMap
     * check username whether already used by userHashMap
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

    public void loadMysqlIntoHashMap() {
        try {
            System.out.println("start connecting to mysql");
            String url = "jdbc:mysql://149.28.128.137:3306/database";
            String user = "";
            String mySQLPassword = "bjTYFTm4N5sRzziR";
            Connection connect = DriverManager.getConnection(url, user, mySQLPassword);
            // url   jdbc:mysql//(server address)/(database name) , user , passwd

            System.out.println("Success connect Mysql server!");

            Statement stmt = connect.createStatement();

            ResultSet rs = stmt.executeQuery("select * from database.db");
            //user 为你表的名称
            while (rs.next()) {

                String studentID = rs.getString("userID");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");

                usernameAndPasswordMap.put(userName, userPassword);  // put userName and userPassword into HashMap

                System.out.println("userID:" + studentID + ""
                        + "\t" + "Name:" + userName + "\t" + "Password:" + userPassword + "\n");
            }
            rs.close();
            connect.close(); // 关闭连接
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }


    public void updateNewUserIntoMysql(String username, String password) {
        try {
            System.out.println("start connecting");
            String url = "jdbc:mysql://149.28.128.137:3306/database";
            String user = "";
            String mySQLPassword = "bjTYFTm4N5sRzziR";
            Connection connect = DriverManager.getConnection(url, user, mySQLPassword);
            // url   jdbc:mysql//(server address)/(database name) , user , passwd
            System.out.println("Success connect Mysql server!");

            username = "'" + username + "'";
            password = "'" + password + "'";
            Statement stmt = connect.createStatement();
            String sql = "INSERT INTO database.db (userID, userName, userPassword, userMark) VALUES (" + "'" + getId() + "'" + ", " + username + ", " + password + ", '0')";
            System.out.println("sign up SQL statement:    " + sql);
            stmt.executeUpdate(sql);

            connect.close(); // Close connection
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }


    private static int getId() {

        long id = Long.parseLong(String.valueOf(System.currentTimeMillis())
                .substring(1, 10)
                .concat(String.valueOf(inc)));

        inc = (inc + 1) % 10;
        return Math.abs((int) id);

    }
}
