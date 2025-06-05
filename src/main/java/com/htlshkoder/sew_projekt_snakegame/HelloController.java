package com.htlshkoder.sew_projekt_snakegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;
public class HelloController {
    @FXML
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private Timeline timeline;

    private static final int SIZE = 20;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private int appleX, appleY;
    private int score = 0;
    private char direction = 'R';
    private LinkedList<int[]> snake = new LinkedList<>();

    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        snake.add(new int[]{10, 10});
        spawnApple();

        timeline = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            move();
            draw();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Platform.runLater(() -> {
            gameCanvas.getScene().setOnKeyPressed(this::handleKeyPress);
        });
    }
}

