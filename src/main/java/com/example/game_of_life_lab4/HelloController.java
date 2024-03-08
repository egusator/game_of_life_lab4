package com.example.game_of_life_lab4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HelloController {

    private GameOfLifeCore gameOfLifeCore = new GameOfLifeCore();

    @FXML
    private Label welcomeText;

    @FXML
    private TextField probabilityTextField;

    private Timeline timeline;

    @FXML
    private GridPane grid;

    @FXML
    protected void onStartStopButtonClick() {
        if (timeline != null) {
            timeline.stop();
            return;
        }

        grid.getChildren().clear();

        timeline = new Timeline(new KeyFrame(
                Duration.millis(50),
                ae -> {
                    boolean[][] values = gameOfLifeCore.newGeneration();
                    drawValues(values);
                }));
        timeline.setCycleCount(5000);
        timeline.play();

    }

    public void drawValues(boolean[][] values) {
        for (int i = 0; i < GameOfLifeCore.SIZE; i++) {
            for (int j = 0; j < GameOfLifeCore.SIZE; j++) {
                Rectangle rectangle = new Rectangle(10, 10);
                if (values[i][j]) {
                    rectangle.setFill(Color.GREEN);
                } else {
                    rectangle.setFill(Color.RED);
                }
                grid.add(rectangle, j, i);
            }
        }
    }

    @FXML
    protected void onGenerateButtonClick() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        boolean[][] values = gameOfLifeCore.generateInitialValues(
                Double.parseDouble(probabilityTextField.getText())
        );

        drawValues(values);
    }
}