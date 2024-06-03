module fr.chess.saechess {
    requires javafx.controls;
    requires javafx.fxml;
            
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens fr.chess.saechess to javafx.fxml;
    exports fr.chess.saechess;
}