package com.htlshkoder.sew_projekt_snakegame;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/htlshkoder/projekt_prov/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snake Game FX");
        stage.setScene(scene);
        stage.show();
        System.out.println("FXML path = " + getClass().getResource("/fxml/game-view.fxml"));

    }

    public static void main(String[] args) {
        launch();
    }
}

