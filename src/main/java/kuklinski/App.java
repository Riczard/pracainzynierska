package kuklinski;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kuklinski.model.Fullerene;
import kuklinski.utils.FXMLUtils;

import java.util.List;

public class App extends Application {

    private static final String MAIN_PANE = "/fxml/MainPane.fxml";

    public static void main(String[] args) {
        int numberOfAtoms = 0;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt");
        Fullerene fullerene = new Fullerene(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullerene.fill(data);
        fullerene.calculateDistanceBetweenAtoms();
        fullerene.calculateEnergyForBonds();
        fullerene.calculateTotalEnergy();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = FXMLUtils.fxmlLoader(MAIN_PANE);
        Scene scene = new Scene(pane);
        scene.setCamera(new PerspectiveCamera());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fullerene");
        primaryStage.show();
    }

    public static Rectangle2D getScreen() {
        return Screen.getPrimary().getBounds();
    }
}
