module com.buckshot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.buckshot to javafx.fxml;
    exports com.buckshot;
}
