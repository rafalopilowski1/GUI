package edu.justsomebody.javafx_project;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class GeneratorThread implements Runnable {

    static int cycles;
    final GamePane gamePane;

    GeneratorThread(GamePane gamePane) {
        this.gamePane = gamePane;
        cycles = 0;
    }

    @Override
    public void run() {
        generate_and_push_rectangle_to_window(4000.0 / (gamePane.numberOfCurrentRectanglesProperty.get() + 1), gamePane);
        cycles++;
    }

    public void generate_and_push_rectangle_to_window(double millis, Pane pane) {
        Rectangle new_rec = new Rectangle();
        new_rec.setFill(Color.TRANSPARENT);
        new_rec.setWidth(50);
        new_rec.setHeight(50);
        new_rec.setOnTouchMoved(event -> from_red_to_deleted(new_rec));
        new_rec.setOnMouseClicked(event -> from_red_to_deleted(new_rec));

        gamePane.numberOfRectanglesProperty.set(gamePane.numberOfRectanglesProperty.get() + 1);
        gamePane.numberOfCurrentRectanglesProperty.set(gamePane.numberOfCurrentRectanglesProperty.get() + 1);

        Platform.runLater(() -> {
            pane.getChildren().add(new_rec);
            new_rec.setX(Math.random() * gamePane.getLayoutBounds().getMaxX());
            new_rec.setY(0);
        });

        FillTransition ft = new FillTransition(new Duration(300), new_rec);
        ft.setToValue(Color.color(0.0, 0.0, 0.6));
        ft.setInterpolator(Interpolator.EASE_OUT);
        Platform.runLater(ft::play);

        TranslateTransition tt = new TranslateTransition(new Duration(millis), new_rec);
        tt.setInterpolator(Interpolator.EASE_IN);
        tt.toYProperty().bind(Bindings.createDoubleBinding(() -> gamePane.getLayoutBounds().getMaxY(), gamePane.layoutBoundsProperty()));
        tt.setOnFinished(event -> {
            if (gamePane.numberOfCurrentRectanglesProperty.get() > 0)
                gamePane.numberOfCurrentRectanglesProperty.set(gamePane.numberOfCurrentRectanglesProperty.get() - 1);
            gamePane.fade_to_delete(new_rec);
        });
        Platform.runLater(tt::play);
    }

    private void from_red_to_deleted(Rectangle new_rec) {
        gamePane.scoreProperty.set(gamePane.scoreProperty.get() + 1);
        if (gamePane.numberOfCurrentRectanglesProperty.get() > 0)
            gamePane.numberOfCurrentRectanglesProperty.set(gamePane.numberOfCurrentRectanglesProperty.get() - 1);
        FillTransition ft = new FillTransition(new Duration(300), new_rec);
        ft.setToValue(Color.RED);
        ft.setOnFinished(event2 -> gamePane.fade_to_delete(new_rec));
        ft.play();
    }
}
