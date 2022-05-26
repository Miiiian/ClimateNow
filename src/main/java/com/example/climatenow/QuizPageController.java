package com.example.climatenow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuizPageController implements Initializable {
    private Stage homeStage;
    private Scene homeScene;
    private Parent homeRoot;

    @FXML
    private ImageView switchToHomePageToo;
    @FXML
    private Button switchToHomeButton;
    @FXML
    private Label QuizQLabel;
    @FXML
    private Button StartB;
    @FXML
    private Label UserQ;
    @FXML
    private Label resultLabel;
    @FXML
    private ChoiceBox<String> QuizQ;

    @FXML
    public static ArrayList<String> Question = new ArrayList<>();
    public static ArrayList<String[]> Answer = new ArrayList<>();

    // Generating the index using Math.random()
    @FXML
    int index = (int)(Math.random() * Question.size());

    //Arrays for each questions' answers
    @FXML
    private static String[] zero = {"To strengthen the country’s ability to deal with impacts of climate change", "To limit global warming to below 2 deg and if possible below 1.5 deg", "To prevent further damage by halting all temperature rising activities"};
    private static String[] one = {"192", "120", "300", "193"};
    private static String[] two = {"Climate change", "Increase in population size", "Pollution", "Supernatural causes"};
    private static String[] three = {"Variations in global temperatures that occurs over time", "The change in weather", "Rise in temperature of the surface of Earth", "Rise of ocean water levels and melting of ice caps"};
    private static String[] four = {"Forms deadly pathogens in sources, making consumption dangerous", "Causes stomachache", "Decreases the aquatic population"};
    private static String[] five = {"The increase of Earth’s temperature", "The increase of the moon’s temperature", "The decrease of Earth’s temperature", "The decrease of the moon’s temperature"};
    private static String[] six = {"The decrease of the moon’s temperature", "Deforestation", "Reforestation", "The burning of fossil fuels", "the increase of greenhouse gases"};
    private static String[] seven = {"Methane gas", "Oxygen", "Nitrous oxide", "Carbon Dioxide"};
    private static String[] eight = {"Greenhouse gases absorbs infrared radiation in Earth’s atmosphere", "this warms the world’s climate", "Greenhouse gases causes oxygen in the atmosphere to rise", "Greenhouse gases cools down the Earth’s temperature", "Greenhouse gases will not affect climate change"};
    private static String[] nine = {"Melts Ice Caps, which increases the sea levels", "Global warming has no side effects"};


    @FXML
    private String userQuestionString[];

    @FXML
    private String userQuestionNow;

    @FXML
    public static HashMap<String, Integer> questionsHashMap = new HashMap<String, Integer>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        // 打印Quiz的内容
        System.out.println(Arrays.toString(Answer.get(index)));


        //Questions for user
        UserQ.setText(Question.get(index));

        userQuestionNow = Question.get(index);
        Question.remove(index);

        // 根据Answer.get(index)来判断哪个数组来放入ChoiceBox
        QuizQ.getItems().addAll(Answer.get(index));
        userQuestionString = Answer.get(index);
        Answer.remove(index);

        // Locking in answers
        QuizQ.setOnAction(this::getChoices);
        QuizQ.valueProperty().addListener((e) -> {
            QuizQ.setDisable(true);
        });

    }

    public void getChoices(ActionEvent event) {
        String myChoices = QuizQ.getValue();
        // 查询myChoices在userQuestionString中的位置
        int index = Arrays.asList(userQuestionString).indexOf(myChoices);

        if (index == questionsHashMap.get(userQuestionNow)) {
            resultLabel.setText("Correct!");
        } else {
            resultLabel.setText("Wrong!");
        }

        QuizQLabel.setText(myChoices);
        // 通过HashMap的第二个参数和if语句来验证用户输入的答案是否正确


    }


    // 刷新UserQ
    public void switchToNextQuiz(ActionEvent event) {
        if (Question.size() == 0) {
            UserQ.setText("You have finished all the Questions");
            QuizQLabel.setText("Please click the Homepage icon.");
            QuizQ.setDisable(true);
        } else {
            try {
                homeRoot = FXMLLoader.load(getClass().getResource("QuizQuestions.fxml"));
                homeScene = new Scene(homeRoot);
                homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                homeStage.setScene(homeScene);
                homeStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void loadQuestionHashMapAndList(){
        Question.add("Which was the aim of the 2015 Paris Agreement that involved climate change?");
        Question.add("How many parties agreed to comply with the 2015 Paris Agreement?");
        Question.add("What is the biggest threat towards development according to SDG 13 (Sustainable Development Goals)?");
        Question.add("What is climate change ?");
        Question.add("What is the effect of rising temperatures to freshwater sources?");
        Question.add("What is global warming?");
        Question.add("What does not contribute to global warming?");
        Question.add("What is not a greenhouse gas?");
        Question.add("How does greenhouse gases contribute to climate change?");
        Question.add("What are the effects of global warming?");
        Answer.add(zero);
        Answer.add(one);
        Answer.add(two);
        Answer.add(three);
        Answer.add(four);
        Answer.add(five);
        Answer.add(six);
        Answer.add(seven);
        Answer.add(eight);
        Answer.add(nine);
        questionsHashMap.put("Which was the aim of the 2015 Paris Agreement that involved climate change?", 1);
        questionsHashMap.put("How many parties agreed to comply with the 2015 Paris Agreement?", 3);
        questionsHashMap.put("What is the biggest threat towards development according to SDG 13 (Sustainable Development Goals)?", 0);
        questionsHashMap.put("What is climate change ?", 0);
        questionsHashMap.put("What is the effect of rising temperatures to freshwater sources?", 0);
        questionsHashMap.put("What is global warming?", 0);
        questionsHashMap.put("What does not contribute to global warming?", 2);
        questionsHashMap.put("What is not a greenhouse gas?", 1);
        questionsHashMap.put("How does greenhouse gases contribute to climate change?", 0);
        questionsHashMap.put("What are the effects of global warming?", 0);
    }

    // switchToHomePage method and switch to home page when click the Homepage icon
    @FXML
    public void switchToHomePage(ActionEvent event) {
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