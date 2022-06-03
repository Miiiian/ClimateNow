/**
 * Sample Skeleton for 'rankingPage.fxml' Controller Class
 */

package com.example.climatenow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static com.example.climatenow.LoginPageController.getUsername;

public class RankingPageController implements Initializable {

    @FXML // fx:id="rankFiveTextField"
    private Text rankFiveTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankFiveTextFieldScore"
    private Text rankFiveTextFieldScore; // Value injected by FXMLLoader

    @FXML // fx:id="rankFourTextField"
    private Text rankFourTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankFourTextFieldScore"
    private Text rankFourTextFieldScore; // Value injected by FXMLLoader

    @FXML // fx:id="rankOneTextField"
    private Text rankOneTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankOneTextFieldScore"
    private Text rankOneTextFieldScore; // Value injected by FXMLLoader

    @FXML // fx:id="rankThreeTextField"
    private Text rankThreeTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankThreeTextFieldScore"
    private Text rankThreeTextFieldScore; // Value injected by FXMLLoader

    @FXML // fx:id="rankTwoTextField"
    private Text rankTwoTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankTwoTextFieldScore"
    private Text rankTwoTextFieldScore; // Value injected by FXMLLoader

    @FXML // fx:id="rankYourselfTextField"
    private Text rankYourselfTextField; // Value injected by FXMLLoader

    @FXML // fx:id="rankYourselfTextFieldScore"
    private Text rankYourselfTextFieldScore; // Value injected by FXMLLoader


    private HashMap<String, Integer> UsernameAndScoreHashmap = new HashMap<>();
    private ArrayList<String> UsernameArrayList = new ArrayList<>();
    private ArrayList<Integer> ScoreArrayList = new ArrayList<>();
    private String usernameNow = getUsername();


    /**
     * Initializes Page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMysqlIntoHashMap(); // Loads username and score into HashMap
        sortHashMapByValue(UsernameAndScoreHashmap, UsernameArrayList, ScoreArrayList); // Sort HashMap by value
        showTopFiveUser(UsernameArrayList, ScoreArrayList); // show top 5 user ranking and score
        showYourself(UsernameArrayList, ScoreArrayList); // show usernameNow's rank and score
    }

    /**
     * @function Connect to online mysql database and load username and score into UsernameAndScoreHashmap
     */
    public void loadMysqlIntoHashMap() {
        try {
            // System.out.println("start connecting to mysql");
            String URL = "jdbc:mysql://***:3306/database";
            String USER = "database";
            String MYSQLPASSWORD = "";
            Connection connect = DriverManager.getConnection(URL, USER, MYSQLPASSWORD);
            // System.out.println("Success connect Mysql server!");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from database.db");

            while (rs.next()) {
                // String studentID = rs.getString("userID");
                String userName = rs.getString("userName");
                Integer userScore = rs.getInt("userMark");
                // put userName and score into UsernameAndScoreHashmap
                UsernameAndScoreHashmap.put(userName, userScore);
            }
            rs.close();
            connect.close(); // 关闭连接
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }

    /**
     * @function 从userNameList和userScoreList显示前五个用户到UserTextField和ScoreTextField
     */
    public void showTopFiveUser(ArrayList<String> userNameList, ArrayList<Integer> userScoreList) {
        // 将前五个用户显示到前五个TextField
        rankOneTextField.setText(UsernameArrayList.get(0));
        rankOneTextFieldScore.setText(ScoreArrayList.get(0).toString());
        rankTwoTextField.setText(UsernameArrayList.get(1));
        rankTwoTextFieldScore.setText(ScoreArrayList.get(1).toString());
        rankThreeTextField.setText(UsernameArrayList.get(2));
        rankThreeTextFieldScore.setText(ScoreArrayList.get(2).toString());
        rankFourTextField.setText(UsernameArrayList.get(3));
        rankFourTextFieldScore.setText(ScoreArrayList.get(3).toString());
        rankFiveTextField.setText(UsernameArrayList.get(4));
        rankFiveTextFieldScore.setText(ScoreArrayList.get(4).toString());

    }

    /**
     * @function 从userNameList内查询到usernameNow,并把usernameNow的分数显示在最后一个TextField
     */

    public void showYourself(ArrayList<String> userNameList, ArrayList<Integer> userScoreList) {
        for (int i = 0; i < userNameList.size(); i++) {
            if (userNameList.get(i).equals(usernameNow)) {
                rankYourselfTextField.setText(userNameList.get(i));
                rankYourselfTextFieldScore.setText(userScoreList.get(i).toString());
            }
        }
    }

    /**
     * @function 储存排序后的用户名和分数到各自的ArrayList中
     */
    public static void sortHashMapByValue(HashMap<String, Integer> map, ArrayList<String> userNameList, ArrayList<Integer> userScoreList) {
        map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> {
                    userNameList.add(e.getKey());
                    userScoreList.add(e.getValue());
                });
    }

    // switchToHomePage method and switch to home page when click the Homepage icon
    @FXML
    public void switchToHomePage(ActionEvent event) {
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
