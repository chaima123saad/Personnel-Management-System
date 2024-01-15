import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelDAOImplDB implements TelDAO {
    private static final String ADD_QUERY = "INSERT INTO telephone ( valeur, type, personne_CIN) VALUES ( ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM telephone WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM telephone";

    @Override
    public void add(NumTel numero) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
            preparedStatement.setString(1, numero.getValeur());
            preparedStatement.setString(2, String.valueOf(numero.getType()));
            preparedStatement.setInt(3, numero.getPersonne_CIN());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout du numéro de téléphone", e);
        }
    }

    @Override
    public NumTel findById(int id) {
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedId = resultSet.getInt("id");
                    String retrievedValeur = resultSet.getString("valeur");
                    char retrievedType = resultSet.getString("type").charAt(0);
                    int retrievedPersonne_CIN = resultSet.getInt("personne_CIN");

                    NumTel numero = new NumTel(retrievedId, retrievedValeur, retrievedType, retrievedPersonne_CIN);

                    return numero;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche du numéro de téléphone par ID", e);
        }
        return null; 
    }

    @Override
    public List<NumTel> findAll() {
        List<NumTel> numeros = new ArrayList<>();
        try (Connection connection = MaConnexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

                int retrievedId = resultSet.getInt("id");
                String retrievedValeur = resultSet.getString("valeur");
                char retrievedType = resultSet.getString("type").charAt(0);
                int retrievedPersonne_CIN = resultSet.getInt("personne_CIN");

                NumTel numero = new NumTel(retrievedId, retrievedValeur, retrievedType, retrievedPersonne_CIN);

                numeros.add(numero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche de tous les numéros de téléphone", e);
        }
        return numeros;
    }
}
