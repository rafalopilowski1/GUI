import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Counters");

        Button aliceIncreaseButton = new Button("+");
        Button aliceDecreaseButton = new Button("-");
        Button beatriceIncreaseButton = new Button("+");
        Button beatriceDecreaseButton = new Button("-");
        Button allIncreaseButton = new Button("+1 all");
        Button allDecreaseButton = new Button("-1 all");
        allIncreaseButton.setMinWidth(80);
        allDecreaseButton.setMinWidth(80);

        Label aliceLabel = new Label("Alice");
        Label beatriceLabel = new Label("Beatrice");
        Label totalLabel = new Label("Tot->");
        Label averageLabel = new Label("Ave->");
        aliceLabel.setRotate(90);
        beatriceLabel.setRotate(90);
        totalLabel.setRotate(90);
        averageLabel.setRotate(90);
        Label totalValueLabel = new Label();
        Label averageValueLabel = new Label();
        Label personsValueLabel = new Label();

        VBox aliceGroup = new VBox(aliceIncreaseButton, aliceLabel, aliceDecreaseButton);
        VBox beatriceGroup = new VBox(beatriceIncreaseButton, beatriceLabel, beatriceDecreaseButton);
        aliceGroup.setSpacing(20);
        beatriceGroup.setSpacing(20);
        aliceGroup.setAlignment(Pos.CENTER);
        beatriceGroup.setAlignment(Pos.CENTER);
        HBox personGroupsLayout = new HBox(aliceGroup, beatriceGroup);
        VBox totalGroupLayout = new VBox(totalLabel, totalValueLabel);
        VBox averageGroupLayout = new VBox(averageLabel, averageValueLabel);
        HBox statsGroupsLayout = new HBox(totalGroupLayout, averageGroupLayout);
        VBox otherGroupsLayout = new VBox(allIncreaseButton, statsGroupsLayout, allDecreaseButton);
        personGroupsLayout.setSpacing(20);
        statsGroupsLayout.setSpacing(20);
        otherGroupsLayout.setSpacing(20);
        totalGroupLayout.setSpacing(20);
        averageGroupLayout.setSpacing(20);
        totalGroupLayout.setAlignment(Pos.CENTER);
        averageGroupLayout.setAlignment(Pos.CENTER);
        personGroupsLayout.setAlignment(Pos.CENTER);
        statsGroupsLayout.setAlignment(Pos.CENTER);
        otherGroupsLayout.setAlignment(Pos.CENTER);
        statsGroupsLayout.setAlignment(Pos.CENTER);

        HBox topGroupsLayout = new HBox(personGroupsLayout, otherGroupsLayout);
        topGroupsLayout.setSpacing(20);
        topGroupsLayout.setAlignment(Pos.CENTER);
        VBox mainGroupsLayout = new VBox(topGroupsLayout, personsValueLabel);
        mainGroupsLayout.setSpacing(20);
        mainGroupsLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainGroupsLayout);


        IntegerProperty aliceInt = new SimpleIntegerProperty(0);
        IntegerProperty beatriceInt = new SimpleIntegerProperty(0);

        StringProperty statsString = new SimpleStringProperty();
        statsString.bind(Bindings.createStringBinding(() -> String.format("Alice: %s; Beatrice: %s", aliceInt.get(), beatriceInt.get()), aliceInt, beatriceInt));

        aliceIncreaseButton.setOnMouseClicked(event -> aliceInt.set(aliceInt.get() + 1));
        beatriceIncreaseButton.setOnMouseClicked(event -> beatriceInt.set(beatriceInt.get() + 1));

        aliceDecreaseButton.setOnMouseClicked(event -> aliceInt.set(aliceInt.get() - 1));
        beatriceDecreaseButton.setOnMouseClicked(event -> beatriceInt.set(beatriceInt.get() - 1));

        allDecreaseButton.setOnMouseClicked(event -> {
            aliceInt.set(aliceInt.get() - 1);
            beatriceInt.set(beatriceInt.get() - 1);
        });
        allIncreaseButton.setOnMouseClicked(event -> {
            aliceInt.set(aliceInt.get() + 1);
            beatriceInt.set(beatriceInt.get() + 1);
        });

        aliceDecreaseButton.disableProperty().bind(aliceInt.lessThanOrEqualTo(0));
        beatriceDecreaseButton.disableProperty().bind(beatriceInt.lessThanOrEqualTo(0));
        allDecreaseButton.disableProperty().bind(aliceInt.lessThanOrEqualTo(0).or(beatriceInt.lessThanOrEqualTo(0)));

        totalValueLabel.textProperty().bind(aliceInt.add(beatriceInt).asString());
        averageValueLabel.textProperty().bind(aliceInt.add(beatriceInt).divide(2).asString());
        personsValueLabel.textProperty().bind(statsString);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
