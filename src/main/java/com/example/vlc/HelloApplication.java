package com.example.vlc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // VBox root = new VBox();
        String style =getClass().getResource("/com/example/vlc/style.css").toExternalForm();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Image image=new Image("/VLC-Media-Player-icon.png");
        ImageView imageView= new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(500);

        Scene scene = new Scene(fxmlLoader.<Parent>load(), 600, 500);
        scene.getStylesheets().add(style);
        stage.setTitle("Senqu Media Player!");
        stage.getIcons().add(new Image("/Semitone-Music.jpg"));
        stage.setTitle("Media Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}