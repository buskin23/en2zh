package com.li.en2zh.ui;

import com.li.en2zh.service.TranslationService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TranslationPopup extends Stage {
    private final TranslationService translationService;

    public TranslationPopup(TranslationService translationService) {
        this.translationService = translationService;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(FloatingButton.mainStage);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        TextField inputTextField = new TextField();
        inputTextField.setPromptText("Enter text here...");

        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPromptText("Translation will appear here.");

        Button translateButton = new Button("Translate");
        translateButton.setOnAction(event -> {
            String textToTranslate = inputTextField.getText();
            String translatedText = translationService.translate(textToTranslate);
            outputTextArea.setText(translatedText);
        });

        layout.getChildren().addAll(inputTextField, translateButton, outputTextArea);
        Scene scene = new Scene(layout, 300, 200);
        setScene(scene);
    }
}
