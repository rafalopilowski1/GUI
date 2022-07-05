module edu.justsomebody.javafx_project {
    requires javafx.controls;
    requires javafx.fxml;



    opens edu.justsomebody.javafx_project to javafx.fxml;
    exports edu.justsomebody.javafx_project;
}