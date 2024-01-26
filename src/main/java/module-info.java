module com.khanelectric.khanelectricsupply {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.khanelectric.khanelectricsupply to javafx.fxml;
    exports com.khanelectric.khanelectricsupply;

    opens com.khanelectric.khanelectricsupply.controllers to javafx.fxml;
    opens com.khanelectric.khanelectricsupply.models to javafx.base;
    exports com.khanelectric.khanelectricsupply.controllers;

}