import java.util.List;

public interface PersonneDAO {
    void add(Personne personne);

    Personne findById(int numcin);

    List<Personne> findAll();
    void update(Personne personne);

    void delete(int numcin);
}
