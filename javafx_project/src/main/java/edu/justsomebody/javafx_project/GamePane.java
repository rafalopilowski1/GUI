package edu.justsomebody.javafx_project;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.math.RoundingMode;
import java.text.DecimalFormat;

class GamePane extends BorderPane {

    IntegerProperty scoreProperty;
    IntegerProperty numberOfRectanglesProperty;
    IntegerProperty numberOfCurrentRectanglesProperty;
    IntegerProperty cycles;
    BooleanProperty gameFinishedProperty;

    BooleanBinding winBinding;

    Pane rectanglesPane;

    Timeline timeline;

    GamePane() {
        scoreProperty = new SimpleIntegerProperty(0);
        numberOfRectanglesProperty = new SimpleIntegerProperty(0);
        numberOfCurrentRectanglesProperty = new SimpleIntegerProperty(0);
        cycles = new SimpleIntegerProperty(0);
        gameFinishedProperty = new SimpleBooleanProperty(false);

        rectanglesPane = new Pane();

        setBackground(Background.fill(Color.color(1, 0.784, 0)));
    }

    public void init(Stage stage, Duration timeOfSpawning, Duration frequency) {
        rectanglesPane.prefHeightProperty().bind(heightProperty());
        rectanglesPane.prefWidthProperty().bind(widthProperty());
        setCenter(rectanglesPane);
        setAlignment(rectanglesPane, Pos.CENTER);

        winBinding = scoreProperty.greaterThanOrEqualTo(numberOfRectanglesProperty.divide(2)).and(gameFinishedProperty);

        GeneratorThread gen_thread = new GeneratorThread(this);

        setUpAnimationTimeline(stage, timeOfSpawning, frequency, gen_thread);

        bindStageTitle(stage);
    }

    private void setUpAnimationTimeline(Stage stage, Duration timeOfSpawning, Duration frequency, GeneratorThread gen_thread) {
        KeyFrame keyframe = new KeyFrame(frequency, event -> gen_thread.run());
        timeline = new Timeline(keyframe);
        timeline.setCycleCount((int) (timeOfSpawning.toSeconds() / frequency.toSeconds()));
        timeline.setOnFinished(event -> {
            gameFinishedProperty.set(true);
            pushResultAlert("You've won", "You've lost", winBinding, stage, timeline);
            stage.setTitle("Game over!");
        });
        timeline.play();
    }

    private void bindStageTitle(Stage stage) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);

        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            stage.setTitle(String.format("You have %s seconds", df.format(timeline.getTotalDuration().toSeconds() - cycles.get() * timeline.getCycleDuration().toSeconds() - newValue.toSeconds())));
            if (newValue.toSeconds() == 0) cycles.set(cycles.get() + 1);
        });
    }

    private void pushResultAlert(String winText, String loseText, BooleanBinding winBinding, Stage stage, Timeline timeline) {
        timeline.stop();
        Alert alert = new Alert(Alert.AlertType.NONE, winBinding.get() ? winText : loseText, ButtonType.CLOSE);
        alert.setResizable(false);
        alert.setTitle("Game over");
        alert.setHeaderText("Game over");
        alert.setOnCloseRequest(event -> stage.close());
        alert.show();
    }

    public void fade_to_delete(Shape new_rec) {
        FillTransition ft = new FillTransition(new Duration(300), new_rec);
        ft.setToValue(Color.TRANSPARENT);
        ft.setOnFinished(event1 -> Platform.runLater(() -> getChildren().remove(new_rec)));
        Platform.runLater(ft::play);
    }
}
