module com.example.femanagerstudents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens com.example.femanagerstudents to javafx.fxml;
    exports com.example.femanagerstudents;
}