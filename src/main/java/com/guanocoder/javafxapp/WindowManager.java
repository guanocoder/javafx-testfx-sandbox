package com.guanocoder.javafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {

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

    private static WindowManager instance;

    private final static int DEFAULT_WIDTH = 480;
    private final static int DEFAULT_HEIGHT = 360;

    private Stage primaryStage;

    private WindowManager() {}

    public static synchronized WindowManager getInstance() {
        if(instance == null) {
            instance = new WindowManager();
        }
        return instance;
    }

    public synchronized void setPrimaryStage(Stage primaryStage) throws CannotReplacePrimaryStageException {
        if(this.primaryStage != null) {
            throw new CannotReplacePrimaryStageException();
        }
        this.primaryStage = primaryStage;
    }

    public void openOnPrimaryStage(String viewPath, String windowTitle) throws IOException, PrimaryStageNotSetException {
        double width, height;
        Stage primaryStage = getPrimaryStage();
        width = primaryStage.getWidth();
        height = primaryStage.getHeight();
        openWindow(getPrimaryStage(), viewPath, windowTitle, width, height);
    }

    // Opens as an independent window
    public void openAsApplicationModal(String viewPath, String windowTitle) throws IOException {
        openAsApplicationModal(viewPath, windowTitle, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void openAsApplicationModal(String viewPath, String windowTitle, int width, int height) throws IOException {
        Stage newStage = new Stage();
        newStage.initModality(Modality.NONE);
        openWindow(newStage, viewPath, windowTitle, width, height);
    }

    // Opens as a child window and prevents focus on parent window
    public void openAsWindowModal(String viewPath, String windowTitle) throws IOException {
        openAsWindowModal(viewPath, windowTitle, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void openAsWindowModal(String viewPath, String windowTitle, int width, int height) throws IOException {
        Stage newStage = new Stage();
        newStage.initOwner(primaryStage);
        newStage.initModality(Modality.WINDOW_MODAL);
        openWindow(newStage, viewPath, windowTitle, width, height);
    }


    private synchronized Stage getPrimaryStage() throws PrimaryStageNotSetException {
        if(this.primaryStage == null) {
            throw new PrimaryStageNotSetException();
        }
        return primaryStage;
    }

    private void openWindow(Stage stage, String viewPath, String windowTitle, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(viewPath));
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
}
