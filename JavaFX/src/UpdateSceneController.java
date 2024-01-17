import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class UpdateSceneController {

    @FXML
    private TextField nom;

    @FXML
    private TextField CIN;

    @FXML
    private TextField prenom;

    @FXML
    private TextField civilite;

    @FXML
    void updatePersonne(ActionEvent event) {
        try {
            String Nom = nom.getText();
            int Cin = Integer.parseInt(CIN.getText());
            String Prenom = prenom.getText();
            String Civilite = civilite.getText();

            Personne updatedPersonne = new Personne(Cin, Nom, Prenom, Civilite, null);
            PersonneDAO personneDAO = new PersonneDAOImplDB();
            personneDAO.update(updatedPersonne);

            showSuccessMessage("Personne mise à jour avec succès!");
            nom.setText(null);
            prenom.setText(null);
            CIN.setText(null);
            civilite.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la mise à jour de la personne");
        }
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
