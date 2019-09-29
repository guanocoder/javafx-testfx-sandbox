package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UserViewController {

    @FXML
    private TextField txtUserName, txtFirstName, txtLastName;

    @FXML
    private DatePicker dateCreated, dateLastLogin;


    private User model;

    public void setModel(User model) {
        this.model = model;
        initializeValues();
        initializeHandlers();
    }

    public void initialize() {
    }

    private void initializeValues() {
        txtUserName.setText(model.getUserName());
        txtFirstName.setText(model.getFirstName());
        txtLastName.setText(model.getLastName());
        dateCreated.setValue(model.getCreatedDate());
        dateLastLogin.setValue(model.getLastLoginDate());
    }

    private void initializeHandlers() {

        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> model.setUserName(newValue));
        txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> model.setFirstName(newValue));
        txtLastName.textProperty().addListener((observable, oldValue, newValue) -> model.setLastName(newValue));

        dateCreated.setOnAction(event -> model.setCreatedDate(dateCreated.getValue()));
        dateLastLogin.setOnAction(event -> model.setLastLoginDate(dateLastLogin.getValue()));
    }

    public void handleCancel() {
        WindowManager.close(this);
    }

    public void handleSave() {
        EventBus.userUpdated(model);
        WindowManager.close(this);
    }
}
