package kuklinski.controller;

import javafx.fxml.FXML;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;

public class Model3dController {

    @FXML
    public Pane pane;

    @FXML
    private void initialize() {
        Camera camera = new PerspectiveCamera();
        Sphere sphere = new Sphere(50);

        Group group = new Group();
        group.translateXProperty().setValue(100);
        group.translateYProperty().setValue(100);
        group.translateZProperty().setValue(0);
        group.getChildren().add(sphere);

        Sphere sphere1 = new Sphere(50);
        sphere1.translateXProperty().setValue(300);
        sphere1.translateYProperty().setValue(300);
        sphere1.translateZProperty().set(-200);


        pane.getChildren().add(group);
        pane.getChildren().add(sphere1);

    }
}
