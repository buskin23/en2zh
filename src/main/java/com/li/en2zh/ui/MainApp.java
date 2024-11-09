package com.li.en2zh.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private FloatingButton floatingButton;

    @Override
    public void start(Stage stage) throws Exception {

        new SystemTrayIcon("/images/icon.png", this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showFloatingButton() {
        if (floatingButton == null) {
            floatingButton = new FloatingButton();
        }
        floatingButton.show();
    }
}
