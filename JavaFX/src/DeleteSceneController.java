import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class DeleteSceneController {

    @FXML
    private TextField CIN;

    @FXML
    void deletePersonne(ActionEvent event) {
        try {
            int Cin = Integer.parseInt(CIN.getText());

            PersonneDAO personneDAO = new PersonneDAOImplDB();
            personneDAO.delete(Cin);

            showSuccessMessage("Personne supprimée avec succès!");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la suppression de la personne");
        }
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
