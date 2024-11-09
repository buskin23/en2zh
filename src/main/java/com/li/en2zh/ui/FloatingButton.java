package com.li.en2zh.ui;

import com.li.en2zh.service.NetworkService;
import com.li.en2zh.service.TranslationService;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FloatingButton {
    private Stage stage;
    private double initialX, initialY;
    public static Stage mainStage;

    public FloatingButton() {
        stage = new Stage();
        initStyle();
        initUI();
        mainStage = stage;
    }

    private void initStyle() {
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    private void initUI() {
        ImageView button = new ImageView(new Image(getClass().getResource("/images/button.png").toExternalForm()));
        button.setPreserveRatio(true);
        button.setFitHeight(48);

        Circle circle = new Circle(button.getFitHeight() / 2);
        circle.setCenterX(circle.getRadius());
        circle.setCenterY(circle.getRadius());
        button.setClip(circle);

        Circle circleBackground = new Circle(circle.getRadius());
        circleBackground.setFill(null);
        circleBackground.setStroke(Color.TRANSPARENT);

        StackPane root = new StackPane();
        root.getChildren().addAll(circleBackground, button);
        Scene scene = new Scene(root, button.getFitHeight(), button.getFitHeight());
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double stageWidth = scene.getWidth();
        double stageHeight = scene.getHeight();

        stage.setX(screenWidth - stageWidth - 30);
        stage.setY(screenHeight - stageHeight - 80);

        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMousePressed(event -> {
            initialX = event.getSceneX();
            initialY = event.getSceneY();
        });
        button.setOnMouseDragged(event -> {
            Platform.runLater(() -> {
                double deltaX = event.getSceneX() - initialX;
                double deltaY = event.getSceneY() - initialY;
                stage.setX(stage.getX() + deltaX);
                stage.setY(stage.getY() + deltaY);
                initialX = event.getSceneX();
                initialY = event.getSceneY();
            });
        });

        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Platform.runLater(() -> {
                    if (stage.isShowing()) {
                        stage.hide();
                    } else {
                        stage.show();
                    }
                    TranslationService translationService = new TranslationService(new NetworkService());
                    new TranslationPopup(translationService).show();
                });
            }
        });

        stage.show();
    }

    public void show() {
        stage.show();
    }
}
