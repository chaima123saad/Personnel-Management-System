import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOImplDB implements PersonneDAO {
    private static final String ADD_QUERY = "INSERT INTO personne (CIN, nom, prenom, civilité) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM personne WHERE CIN = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM personne";
    private static final String UPDATE_QUERY = "UPDATE personne SET nom = ?, prenom = ?, civilité = ? WHERE CIN = ?";
    private static final String DELETE_QUERY = "DELETE FROM personne WHERE CIN = ?";
    @Override
    public void add(Personne personne) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
            preparedStatement.setInt(1, personne.getNumcin());
            preparedStatement.setString(2, personne.getNom());
            preparedStatement.setString(3, personne.getPrenom());
            preparedStatement.setString(4, personne.getCivilite());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de la personne", e);
        }
    }

    @Override
    public Personne findById(int numcin) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setInt(1, numcin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedNumcin = resultSet.getInt("CIN");
                    String retrievedNom = resultSet.getString("nom");
                    String retrievedPrenom = resultSet.getString("prenom");
                    String retrievedCivilite = resultSet.getString("civilité");
                    List<NumTel> retrievedNumeros = getNumerosForPersonne(retrievedNumcin);

                    Personne personne = new Personne(retrievedNumcin, retrievedNom, retrievedPrenom, retrievedCivilite, retrievedNumeros);

                    return personne;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche par ID", e);
        }
        return null;
    }

    @Override
    public List<Personne> findAll() {
        List<Personne> personnes = new ArrayList<>();
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

                int retrievedNumcin = resultSet.getInt("CIN");
                String retrievedNom = resultSet.getString("nom");
                String retrievedPrenom = resultSet.getString("prenom");
                String retrievedCivilite = resultSet.getString("civilité");
                List<NumTel> retrievedNumeros = getNumerosForPersonne(retrievedNumcin);

                Personne personne = new Personne(retrievedNumcin, retrievedNom, retrievedPrenom, retrievedCivilite, retrievedNumeros);

                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche de toutes les personnes", e);
        }
        return personnes;
    }

    private List<NumTel> getNumerosForPersonne(int numcin) {
        List<NumTel> numeros = new ArrayList<>();
        
        String query = "SELECT * FROM telephone WHERE personne_CIN = ?";
        
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numcin);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String valeur = resultSet.getString("valeur");
                    char type = resultSet.getString("type").charAt(0);
                    
                    NumTel numTel = new NumTel(id, valeur, type, numcin);
                    
                    numeros.add(numTel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des numéros de téléphone", e);
        }
    
        return numeros;
    }
    @Override
    public void update(Personne personne) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, personne.getNom());
            preparedStatement.setString(2, personne.getPrenom());
            preparedStatement.setString(3, personne.getCivilite());
            preparedStatement.setInt(4, personne.getNumcin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de la personne", e);
        }
    }

    @Override
    public void delete(int numcin) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, numcin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la personne", e);
        }
    }
}
