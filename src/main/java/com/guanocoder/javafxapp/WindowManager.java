package com.guanocoder.javafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class WindowManager<T> {

    private static Map<Object, WindowManager> controllerMap = new Hashtable<>();

    public static class CannotReplacePrimaryStageException extends Exception {
        public CannotReplacePrimaryStageException() {
            super("Once set, primary stage cannot be replaced!");
        }
    }

    public static class PrimaryStageNotSetException extends Exception {
        public PrimaryStageNotSetException() {
            super("Primary stage not set on WindowManager!");
        }
    }

    private static Stage primaryStage;
    private final static int DEFAULT_WIDTH = 480;
    private final static int DEFAULT_HEIGHT = 360;

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private T controller;

    private WindowManager(Stage stage, String viewPath, String title, double width, double height) throws IOException {
        this.stage = stage;
        loader = new FXMLLoader();
        root = loader.load(getClass().getResourceAsStream(viewPath));
        controller = loader.getController();
        scene = new Scene(root, width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> controllerMap.remove(controller));
        controllerMap.put(controller, this);
    }

    public static WindowManager create(String viewPath, String title) throws IOException, PrimaryStageNotSetException {
        double width, height;
        Stage primaryStage = getPrimaryStage();
        width = primaryStage.getWidth();
        height = primaryStage.getHeight();
        return WindowManager.create(viewPath, title, width, height);
    }

    public static WindowManager create(String viewPath, String title, double width, double height) throws IOException, PrimaryStageNotSetException {
        return WindowManager.create(getPrimaryStage(), viewPath, title, width, height);
    }

    public static WindowManager create(Stage stage, String viewPath, String title) throws IOException {
        return WindowManager.create(stage, viewPath, title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static WindowManager create(Stage stage, String viewPath, String title, double width, double height) throws IOException {
        return new WindowManager(stage, viewPath, title, width, height);
    }

    public static synchronized void setPrimaryStage(Stage primaryStage) throws CannotReplacePrimaryStageException {
        if(WindowManager.primaryStage != null) {
            throw new CannotReplacePrimaryStageException();
        }
        WindowManager.primaryStage = primaryStage;
    }

    public static synchronized Stage getPrimaryStage() throws PrimaryStageNotSetException {
        if(WindowManager.primaryStage == null) {
            throw new PrimaryStageNotSetException();
        }
        return WindowManager.primaryStage;
    }

    public static synchronized void close(Object controller) {
        if(controllerMap.containsKey(controller)) {
            WindowManager window = controllerMap.remove(controller);
            window.stage.close();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public T getController() {
        return controller;
    }

    public void show() {
        stage.show();
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
