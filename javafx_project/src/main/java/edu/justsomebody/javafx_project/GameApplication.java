package edu.justsomebody.javafx_project;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameApplication extends Application {
    static GamePane gamePane;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        BorderPane mainPane = new BorderPane();

        gamePane = new GamePane();

        mainPane.setCenter(gamePane);

        Label scoreLabel = new Label();
        scoreLabel.textProperty().bind(Bindings.createStringBinding(() -> String.format("Score total: %s%%", 100 * gamePane.scoreProperty.get() / (gamePane.numberOfRectanglesProperty.get() == 0 ? 1 : gamePane.numberOfRectanglesProperty.get())), gamePane.scoreProperty, gamePane.numberOfRectanglesProperty));

        TitledPane titledScorePane = new TitledPane("Score", scoreLabel);
        titledScorePane.setExpanded(true);
        titledScorePane.setCollapsible(false);
        mainPane.setBottom(titledScorePane);

        Scene scene = new Scene(mainPane, 512, 512);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();

        gamePane.init(stage, Duration.minutes(1), Duration.millis(500));
    }
}