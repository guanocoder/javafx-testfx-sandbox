package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private static List<WeakReference<EventBusSubscriber>> subscribers = new ArrayList<>();

    public static void createUser() {
        try {
            Stage stage = new Stage();
            WindowManager<UserViewController> window = WindowManager.create(stage, "/UserView.fxml", "Create User");
            window.getController().setModel(new User());
            window.show();
        } catch(IOException e) {}
    }

    public static void userCreated(User user) {
        for(WeakReference<EventBusSubscriber> subscriberRef : subscribers) {
            EventBusSubscriber subscriber = subscriberRef.get();
            if(subscriber != null)
                subscriber.userCreated(user);
        }
        clearWeakLinks();
    }

    public static void updateUser(User user) {
        try {
            Stage stage = new Stage();
            WindowManager<UserViewController> window = WindowManager.create(stage, "/UserView.fxml", "Edit User");
            window.getController().setModel(user);
            window.show();
        } catch(IOException e) {}
    }

    public static void userUpdated(User user) {
        for(WeakReference<EventBusSubscriber> subscriberRef : subscribers) {
            EventBusSubscriber subscriber = subscriberRef.get();
            if(subscriber != null)
                subscriber.userUpdated(user);
        }
        clearWeakLinks();
    }

    public static void deleteUser(User user) {
        // TODO: Ask if user is sure about this
        userDeleted(user);
    }

    public static void userDeleted(User user) {
        for(WeakReference<EventBusSubscriber> subscriberRef : subscribers) {
            EventBusSubscriber subscriber = subscriberRef.get();
            if(subscriber != null)
                subscriber.userDeleted(user);
        }
        clearWeakLinks();
    }

    public static void subscribe(EventBusSubscriber subscriber) {
        subscribers.add(new WeakReference(subscriber));
    }

    private static void clearWeakLinks() {
        int index = 0;
        while(index < subscribers.size()) {
            WeakReference<EventBusSubscriber> weakLink = subscribers.get(index);
            if(weakLink.get() == null) {
                subscribers.remove(index);
                continue;
            }
            index++;
        }
    }
}
