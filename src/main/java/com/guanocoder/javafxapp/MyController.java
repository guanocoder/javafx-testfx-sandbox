package com.guanocoder.javafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MyController {

    @FXML
    private Label lblMasthead;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnSubmit;

    @FXML
    private HBox hbxContainer;


    private Button btnContinue;
    private EventHandler<ActionEvent> continueHandler;

    public MyController() {
        this.continueHandler = event -> {
            lblMasthead.setText("TO BE CONTINUED...");
        };
    }

    public void submitHandler() {
        lblMasthead.setText(txtInput.getText());
        txtInput.clear();
        if(btnContinue == null) {
            simulateDelay(this::createContinueButton, 500 + (int)(Math.random() * 1000));
        }
    }

    private void createContinueButton() {
        btnContinue = new Button("Continue");
        btnContinue.setMnemonicParsing(false);
        btnContinue.setId("btnContinue");
        btnContinue.setOnAction(continueHandler);
        Platform.runLater(() -> {
            hbxContainer.getChildren().add(btnContinue);
        });
    }

    public static void simulateDelay(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

}

