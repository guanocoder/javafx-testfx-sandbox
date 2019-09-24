package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
        Date createdDate, lastLoginDate;
        createdDate = model.getCreatedDate();
        lastLoginDate = model.getLastLoginDate();
        dateCreated.setValue(LocalDate.of(createdDate.getYear(), createdDate.getMonth(), createdDate.getDay()));
        dateLastLogin.setValue(LocalDate.of(lastLoginDate.getYear(), lastLoginDate.getMonth(), lastLoginDate.getDay()));
    }

    private void initializeHandlers() {

        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> model.setUserName(newValue));
        txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> model.setUserName(newValue));
        txtLastName.textProperty().addListener((observable, oldValue, newValue) -> model.setUserName(newValue));

        dateCreated.setOnAction(event -> {
            model.setCreatedDate(
                    Date.from(dateCreated.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
            );
        });

        dateLastLogin.setOnAction(event -> {
            model.setLastLoginDate(
                    Date.from(dateLastLogin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
            );
        });
    }
}
