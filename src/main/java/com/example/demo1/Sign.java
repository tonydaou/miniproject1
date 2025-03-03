package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sign extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign In Form v3");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        grid.add(btn, 1, 4);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 0, 5, 2, 1);

        Text welcomeMsg = new Text("");
        welcomeMsg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        StackPane welcomeStack = new StackPane();
        welcomeStack.getChildren().add(welcomeMsg);
        Scene welcomeScene = new Scene(welcomeStack, 300, 275);
        Stage welcomeStage = new Stage();
        welcomeStage.setScene(welcomeScene);
        welcomeStage.setResizable(false);

        btn.setOnAction(evt -> {
                    String username = userTextField.getText();
                    String password = pwBox.getText();

                    if(username.equals("") || password.equals("")) {
                        errorMsg.setText("Username and password are required!");
                    }
                    else if(!username.equals("admin") || !password.equals("123456")) {
                        errorMsg.setText("Incorrect username and/or password");
                    }
                    else if(username.equals("admin") && password.equals("123456")) {
                        welcomeMsg.setText("Welcome " + username + "!");
                        welcomeStage.setX(primaryStage.getX() + 100);
                        welcomeStage.setY(primaryStage.getY() + 100);
                        welcomeStage.show();
                    }
                }
        );

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
