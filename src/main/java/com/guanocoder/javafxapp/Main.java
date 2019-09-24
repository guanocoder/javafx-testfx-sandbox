package com.guanocoder.javafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowManager.setPrimaryStage(primaryStage);
        WindowManager.create("/MyView.fxml", "Hello World!").show();
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru", "RU"));
        launch(args);
    }
}
