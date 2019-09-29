package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


public class MyListViewController implements EventBusSubscriber {

    @FXML private TableView<User> tableUserList;
    @FXML private TableColumn columnUserName, columnFirstName, columnLastName, columnCreatedDate, columnLastLoginDate;

    public MyListViewController() {
        EventBus.subscribe(this);
    }

    private ObservableList<User> userList = FXCollections.observableArrayList(
            new User("ugly_joe", "Joseph", "Palacio", Utils.localDateFromString("08/07/2016"), Utils.localDateFromString("18/03/2019")),
            new User("pretty_nina", "Nina", "LaFrein", Utils.localDateFromString("23/11/2016"), Utils.localDateFromString("19/03/2018")),
            new User("twat_98", "Tom", "Jones", Utils.localDateFromString("11/02/2017"), Utils.localDateFromString("27/09/2019")),
            new User("wonka_02", "Will", "Henrickson", Utils.localDateFromString("23/04/2017"), Utils.localDateFromString("12/09/2018")),
            new User("scoundrel", "Ruprecht", "Shunzerberg", Utils.localDateFromString("17/03/2018"), Utils.localDateFromString("29/11/2018")),
            new User("admin", "Joanna", "Wilson", Utils.localDateFromString("12/08/2015"), Utils.localDateFromString("01/08/2019")),
            new User("alonzo", "Alan", "Queen", Utils.localDateFromString("05/01/2019"), Utils.localDateFromString("19/06/2019"))
    );

    // Holy shit, is this called via reflection? is this Java or what?
    public void initialize() {
        columnUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        columnCreatedDate.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("createdDate"));
        columnCreatedDate.setCellFactory(column -> createFormattedDateCell());
        columnLastLoginDate.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("lastLoginDate"));
        columnLastLoginDate.setCellFactory(column -> createFormattedDateCell());
        tableUserList.setItems(userList);

        tableUserList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("Row selected: " + ((newValue != null) ? newValue.getUserName() : null));
        });

        tableUserList.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                //System.out.println("Double click detected on " + tableUserList.getSelectionModel().getSelectedItem().getUserName());
                EventBus.updateUser(tableUserList.getSelectionModel().getSelectedItem());
            }
        });
    }

    private TableCell<User, LocalDate> createFormattedDateCell() {
        return new TableCell<User, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(Utils.dateStringFromLocalDate(item));
                }
            }
        };
    }

    @Override
    public void userUpdated(User user) {
        int index = userList.indexOf(user);
        if(index >= 0)
            userList.set(index, user);
    }

    @Override
    public void userDeleted(User user) {
        userList.remove(user);
    }

    @Override
    public void userCreated(User user) {
        userList.add(user);
    }
}
