package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyListViewController {

    @FXML private TableView tableUserList;
    @FXML private TableColumn columnUserName, columnFirstName, columnLastName, columnCreatedDate, columnLastLoginDate;

    private ObservableList<User> userList = FXCollections.observableArrayList(
            new User("ugly_joe", "Joseph", "Palacio", new Date(2016, 7, 8), new Date()),
            new User("pretty_nina", "Nina", "LaFrein", new Date(2016,11, 23), new Date()),
            new User("twat_98", "Tom", "Jones", new Date(2017, 2, 11), new Date()),
            new User("wonka_02", "Will", "Henrickson", new Date(2017, 4, 23), new Date()),
            new User("scoundrel", "Ruprecht", "Shunzerberg", new Date(2018, 3, 17), new Date()),
            new User("admin", "Joanna", "Wilson", new Date(2015, 8, 12), new Date()),
            new User("alonzo", "Alan", "Queen", new Date(2019, 1, 5), new Date())
    );

    // Holy shit, is this called via reflection? is this Java or what?
    public void initialize() {
        columnUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        columnCreatedDate.setCellValueFactory(new PropertyValueFactory<User, Date>("createdDate"));
        columnCreatedDate.setCellFactory(column -> createFormattedDateCell("dd/MM/yyyy"));
        columnLastLoginDate.setCellValueFactory(new PropertyValueFactory<User, Date>("lastLoginDate"));
        columnLastLoginDate.setCellFactory(column -> createFormattedDateCell("dd/MM/YYYY HH:mm"));
        tableUserList.setItems(userList);
    }

    private TableCell<User, Date> createFormattedDateCell(String dateFormatString) {
        return new TableCell<User, Date>() {
            private SimpleDateFormat format = new SimpleDateFormat(dateFormatString);
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        };
    }
}
