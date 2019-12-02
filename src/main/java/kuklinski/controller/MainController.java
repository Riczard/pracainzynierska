package kuklinski.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import kuklinski.utils.FXMLUtils;

public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private void initialize() {
        setCenter("/fxml/Model3dPane.fxml");
    }

    private void setCenter(String fxmlPath) {
        borderPane.setCenter(FXMLUtils.fxmlLoader(fxmlPath));
    }

}
