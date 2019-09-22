package com.guanocoder.javafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream("/MyView.fxml"));
        //Parent root = (Parent) loader.load(getClass().getResourceAsStream("/MyListView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 480, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru", "RU"));
        launch(args);
    }
}
