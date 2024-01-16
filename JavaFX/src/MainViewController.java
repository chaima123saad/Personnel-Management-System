import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Pane contentPane;

    @FXML
    void openAddScene() {
        loadFXML("AddScene.fxml");
    }

    @FXML
    void openDeleteScene() {
        loadFXML("DeleteScene.fxml");
    }

    @FXML
    void openUpdateScene() {
        loadFXML("UpdateScene.fxml");
    }

    @FXML
    void openListePersonnesScene() {
        loadFXML("List.fxml");
    }

    private void loadFXML(String fxmlFile) {
        try {
            // Load the FXML file dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node node = loader.load();

            // Clear the content of the Pane and add the new node
            contentPane.getChildren().clear();
            contentPane.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message)
        }
    }
}
