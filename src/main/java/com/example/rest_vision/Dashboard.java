package com.example.rest_vision;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Dashboard extends Application {
    double x, y=0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1336, 700);
        Image image = new Image("file:///F:/projet javaFX/rest_vision/src/main/resources/com/example/rest_vision/public/img/utils/icon-1.png");
        stage.getIcons().add(image);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Dashboard");

        // ces deux evenements permettent de deplacer l'ihm puisse
        // que la decoration par defaut est desactivee
        scene.setOnMousePressed(event ->{
           x = event.getSceneX();
           y = event.getSceneY();
        });

        scene.setOnMouseDragged(event ->{
           stage.setX(event.getScreenX() - x);
           stage.setY(event.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}