import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private TextField nom;

    @FXML
    private TextField CIN;

    @FXML
    private TextField prenom;

    @FXML
    private TextField civilite;

    @FXML
    void add(ActionEvent event) {
        try {
            Stage mainWindow = (Stage) nom.getScene().getWindow();

            String Nom = nom.getText();
            String Prenom = prenom.getText();
            String Cin = CIN.getText();
            String Civilite = civilite.getText();
            Personne personne = new Personne(Integer.parseInt(Cin), Nom, Prenom, Civilite, null);
            PersonneDAO personneDAO = new PersonneDAOImplDB();

            try {
                personneDAO.add(personne);
                // Show a success alert
                showAlert("Succès", "Personne ajoutée avec succès!", Alert.AlertType.INFORMATION);
                nom.setText(null);
                prenom.setText(null);
                CIN.setText(null);
                civilite.setText(null);

            } catch (Exception e) {
                // Show an error alert
                showAlert("Erreur", "Personne déjà existante", Alert.AlertType.ERROR);
            }

            Personne personneRecuperee = personneDAO.findById(Integer.parseInt(Cin));

            if (personneRecuperee != null) {
                System.out.println("Informations de la personne récupérée depuis la base de données:");
                System.out.println("Numéro CIN: " + personneRecuperee.getNumcin());
                System.out.println("Nom: " + personneRecuperee.getNom());
                System.out.println("Prénom: " + personneRecuperee.getPrenom());
                System.out.println("Civilité: " + personneRecuperee.getCivilite());
            } else {
                System.out.println("Personne non trouvée dans la base de données.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

