package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class MyController {

    @FXML
    private Label lblMasthead;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnSubmit;

    @FXML
    private HBox hbxContainer;

    @FXML
    private DatePicker datePicker;

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

    public void initialize() {
        //datePicker.setValue(LocalDate.now());
        datePicker.setValue(LocalDate.of(2019, 9, 21));
        datePicker.setShowWeekNumbers(false);
        datePicker.setOnAction(event -> showInformation("You have chosen: " + datePicker.getValue()));
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

    // practically like javascript's setTimeout() method
    private static void simulateDelay(Runnable runnable, int delay){
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

    public void showInformation() {
        showInformation(
                "This is an information alert!",
                "This is the description for the information alert that is displayed to the user."
        );
    }

    public void showWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("This is a warning alert!");
        alert.setContentText("This is the warning content message that is displayed to the user.");
        alert.showAndWait();
    }

    public void showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm!");
        alert.setContentText("This is the confirmation message that is displayed to the user.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            showInformation("You've chosen \"OK\"");
        } else {
            showInformation("You've cancelled");
        }
    }

    public void showInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input dialog");
        dialog.setHeaderText("Input your input");
        dialog.setContentText("This is the content text of an input dialog");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            if(!result.get().isEmpty())
                showInformation("You have input: " + result.get());
            else
                showInformation("You haven't input a thing!");
        } else {
            showInformation("You've cancelled!");
        }
    }

    private void showInformation(String headerText) {
        this.showInformation(headerText, null);
    }

    private void showInformation(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information alert");
        alert.setHeaderText(headerText);
        if(contentText != null && !contentText.isEmpty()) {
            alert.setContentText(contentText);
        }
        alert.showAndWait();
    }

    public void openTableViewOnMainWindow() throws IOException, WindowManager.PrimaryStageNotSetException {

        WindowManager.create("/MyListView.fxml", "TableView example").show();
    }

    public void openTableViewAsSeparateWindow()throws IOException {
        Stage newStage = new Stage();
        newStage.initModality(Modality.NONE);
        WindowManager.create(newStage,"/MyListView.fxml", "TableView example", 480, 275).show();
    }

    public void openTableViewAsChildWindow() throws IOException, WindowManager.PrimaryStageNotSetException {
        Stage newStage = new Stage();
        newStage.initOwner(WindowManager.getPrimaryStage());
        newStage.initModality(Modality.WINDOW_MODAL);
        WindowManager.create(newStage,"/MyListView.fxml", "TableView example", 480, 275).show();
    }

}

