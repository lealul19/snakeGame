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

    private void spawnApple() {
        Random rand = new Random();
        appleX = rand.nextInt(WIDTH);
        appleY = rand.nextInt(HEIGHT);
    }

    private void move() {
        int[] head = snake.getFirst().clone();
        switch (direction) {
            case 'U' -> head[1]--;
            case 'D' -> head[1]++;
            case 'L' -> head[0]--;
            case 'R' -> head[0]++;
        }

        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT ||
                snake.stream().anyMatch(p -> p[0] == head[0] && p[1] == head[1])) {
            timeline.stop();
            gc.setFill(Color.YELLOW);
            gc.fillText("Game Over! Final Score: " + score, WIDTH * SIZE / 2 - 80, HEIGHT * SIZE / 2);
            return;
        }

        snake.addFirst(head);

        if (head[0] == appleX && head[1] == appleY) {
            spawnApple();
            score++;
        } else {
            snake.removeLast();
        }
    }

    private void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * SIZE, HEIGHT * SIZE);

        gc.setFill(Color.RED);
        gc.fillOval(appleX * SIZE, appleY * SIZE, SIZE, SIZE);

        gc.setFill(Color.LIMEGREEN);
        for (int[] p : snake) {
            gc.fillRect(p[0] * SIZE, p[1] * SIZE, SIZE - 1, SIZE - 1);
        }
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial", 18));
        gc.fillText("Score: " + score, 10, 20);
    }



}