package com.buckshot;

import com.buckshot.Controller.PrimaryController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) {
        stage.setTitle("Buckshot Roulette Tracker");
        PrimaryController controller = new PrimaryController();
        Scene scene = new Scene(controller.build(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
