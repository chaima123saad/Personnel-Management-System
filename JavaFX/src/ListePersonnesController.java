

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class ListePersonnesController {

    @FXML
    private ListView<Personne> personnesListView;

    private PersonneDAO personneDAO = new PersonneDAOImplDB();

    public void initialize() {
        // Initialize the list view with data from the database
        refreshList();
    }

    private void refreshList() {
        // Fetch all persons from the database
        List<Personne> personnes = personneDAO.findAll();

        // Create an ObservableList from the fetched data
        ObservableList<Personne> observablePersonnes = FXCollections.observableArrayList(personnes);

        // Bind the data to the ListView
        personnesListView.setItems(observablePersonnes);
    }
}
