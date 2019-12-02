package kuklinski.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FXMLUtils {

    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
    }
}
