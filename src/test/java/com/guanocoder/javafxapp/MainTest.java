package com.guanocoder.javafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.loadui.testfx.GuiTest.find;

public class MainTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream("/MyView.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testTextInput() throws Exception {
        Label lblMasthead = (Label) find("#lblMasthead");
        clickOn("#txtInput");
        write("This is a test!");
        clickOn("#btnSubmit");
        assertThat(lblMasthead.getText(), is("This is a test!"));

        // This call generates a screenshot everytime the expected node not found
        //waitUntil("Continue",  Matchers.is(VisibleNodesMatcher.visible()), 15);

        while(!lookup("Continue").tryQuery().isPresent()) Thread.sleep(300);

        clickOn("Continue");
        assertThat(lblMasthead.getText(), is("TO BE CONTINUED..."));
    }

}