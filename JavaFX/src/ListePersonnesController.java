import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListePersonnesController {

    @FXML
    private TableView<Personne> personnesTableView;

    private PersonneDAO personneDAO = new PersonneDAOImplDB();

    public void initialize() {
        // Initialize the table view with data from the database
        initializeTableView();

        // Fetch and display data
        refreshList();
    }

    private void initializeTableView() {
        // Create columns for the table view
        TableColumn<Personne, Integer> cinColumn = new TableColumn<>("CIN");
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("numcin"));

        TableColumn<Personne, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Personne, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Personne, String> civiliteColumn = new TableColumn<>("Civilite");
        civiliteColumn.setCellValueFactory(new PropertyValueFactory<>("civilite"));

        // Add columns to the table view
        personnesTableView.getColumns().addAll(cinColumn, nomColumn, prenomColumn, civiliteColumn);
    }

    private void refreshList() {
        // Fetch all persons from the database
        List<Personne> personnes = personneDAO.findAll();

        // Create an ObservableList from the fetched data
        ObservableList<Personne> observablePersonnes = FXCollections.observableArrayList(personnes);

        // Bind the data to the TableView
        personnesTableView.setItems(observablePersonnes);
    }
}
