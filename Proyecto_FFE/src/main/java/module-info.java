module org.example.Proyecto_FFE {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.Proyecto_FFE to javafx.fxml;
    opens modelos to javafx.base;
    exports org.example.Proyecto_FFE;
}