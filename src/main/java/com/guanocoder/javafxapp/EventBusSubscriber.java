package com.guanocoder.javafxapp;

import com.guanocoder.javafxapp.models.User;

public interface EventBusSubscriber {

    public void userUpdated(User user);
    public void userDeleted(User user);
    public void userCreated(User user);

}
