package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class hello extends Application {
    private Button btnHello;
    @Override
    public void start(Stage primaryStage) {

        btnHello = new Button();
        btnHello.setText("oul hi");

        btnHello.setOnAction(evt -> System.out.println("Hello W"));
        StackPane root = new StackPane(); // The root of scene graph is a layout noc
        root.getChildren().add(btnHello); // The root node adds Button as a child
        Scene scene = new Scene(root, 300, 100); // Construct a scene given the roo
        primaryStage.setScene(scene); // The stage sets scene
        primaryStage.setTitle("Hello"); // Set window's title
        primaryStage.show();
// Set visible (show it)
    }
    public static void main(String[] args) {
    launch(args);}
}