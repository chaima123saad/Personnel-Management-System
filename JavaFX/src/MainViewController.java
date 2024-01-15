import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    void openAddScene(ActionEvent event) throws IOException {
        openScene("AddScene.fxml", "Add Personne");
    }

    @FXML
    void openDeleteScene(ActionEvent event) throws IOException {
        openScene("DeleteScene.fxml", "Delete Personne");
    }

    @FXML
    void openUpdateScene(ActionEvent event) throws IOException {
        openScene("UpdateScene.fxml", "Update Personne");
    }

    private void openScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
