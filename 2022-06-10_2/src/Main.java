import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

enum DiscountTypes {
    FullPrice,
    Senior,
    Student;

    @Override
    public String toString() {
        return switch (this) {
            case Senior -> "Senior";
            case Student -> "Student";
            case FullPrice -> "Full price";
        };
    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tickets");
        Label descLabel = new Label();
        ObservableMap<String, Double> destinationMap = FXCollections.emptyObservableMap();
        ComboBox<String> destinationCombo = new ComboBox<String>(destinationMap);
        ObservableList<DiscountTypes> discountList = FXCollections.observableList(Arrays.asList(DiscountTypes.values()));
        ComboBox<DiscountTypes> discountCombo = new ComboBox<DiscountTypes>(discountList);
        VBox mainLayout = new VBox(destinationCombo, discountCombo);
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
