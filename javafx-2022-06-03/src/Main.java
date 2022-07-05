import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bałwany");

        Circle kolo1 = new Circle();
        Circle kolo2 = new Circle();
        Circle kolo3 = new Circle();

        kolo1.setFill(Color.WHITE);
        kolo2.setFill(Color.WHITE);
        kolo3.setFill(Color.WHITE);

        VBox vbox = new VBox(kolo1, kolo2, kolo3);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);

        kolo1.radiusProperty().bind(scene.heightProperty().divide(12));
        kolo2.radiusProperty().bind(scene.heightProperty().divide(6));
        kolo3.radiusProperty().bind(scene.heightProperty().divide(4));

        scene.setFill(Color.BLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
