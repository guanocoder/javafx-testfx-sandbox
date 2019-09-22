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
        WindowManager.getInstance().setPrimaryStage(primaryStage);
        WindowManager.getInstance().openOnPrimaryStage("/MyView.fxml", "Hello World!");
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru", "RU"));
        launch(args);
    }
}
